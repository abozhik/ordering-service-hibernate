package abozhik.service;

import abozhik.DataSource;
import abozhik.generator.TestDataGenerator;
import abozhik.model.Ordering;
import abozhik.model.OrderingItem;
import abozhik.reader.DatabaseCredentials;
import abozhik.repository.OrderingItemsRepositoryImpl;
import abozhik.repository.OrderingRepositoryImpl;
import abozhik.sessionmanager.SessionManagerHibernate;
import abozhik.util.HibernateUtils;
import abozhik.util.JdbcUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OrderingServiceTest {

    private static final PostgreSQLContainer<PostgreSQLContainerShared> POSTGRE_SQL_CONTAINER = PostgreSQLContainerShared.getInstance();
    public static final DataSource dataSource;

    public static final long DEFAULT_ID = 1L;

    static {
        POSTGRE_SQL_CONTAINER.start();
        DatabaseCredentials credentials = new DatabaseCredentials(
                POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                POSTGRE_SQL_CONTAINER.getUsername(),
                POSTGRE_SQL_CONTAINER.getPassword());
        dataSource = new DataSource(credentials);
    }

    private final OrderingService orderingService;
    private final TestDataGenerator testDataGenerator;

    public OrderingServiceTest() {
        Configuration configuration = HibernateUtils.getConfiguration(
                POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                POSTGRE_SQL_CONTAINER.getUsername(),
                POSTGRE_SQL_CONTAINER.getPassword());
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(configuration, Ordering.class, OrderingItem.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        var orderingItemsRepository = new OrderingItemsRepositoryImpl(sessionManager);
        sessionManager = new SessionManagerHibernate(sessionFactory);
        var orderingRepository = new OrderingRepositoryImpl(sessionManager);
        orderingService = new OrderingServiceImpl(orderingRepository, orderingItemsRepository);
        testDataGenerator = new TestDataGenerator();
    }

    @Test
    void testCreateOrdering() throws SQLException {
        //given
        var oldCountOrdering = getCountOrdering();
        var oldCountOrderingItems = getCountOrderingItems();

        //when
        var generatedOrdering = testDataGenerator.generateOrdering();
        var orderingItemList = testDataGenerator.generateOrderingItemList(3);
        generatedOrdering.setOrderingItems(orderingItemList);
        var ordering = orderingService.saveOrUpdateOrdering(generatedOrdering);

        //then
        var newCountOrdering = getCountOrdering();
        var newCountOrderingItems = getCountOrderingItems();
        assertThat(ordering).isPresent();
        assertThat(getCountOrderingItemsByOrderingId(ordering.get().getId())).isEqualTo(orderingItemList.size());
        assertThat(oldCountOrdering + 1).isEqualTo(newCountOrdering);
        assertThat(oldCountOrderingItems + orderingItemList.size()).isEqualTo(newCountOrderingItems);
    }

    @Test
    void testAddItemToOrdering() throws SQLException {
        //given
        var oldCountOrderingItems = getCountOrderingItemsByOrderingId(DEFAULT_ID);
        var orderingItem = testDataGenerator.generateOrderingItem();
        //when
        orderingService.addItemToOrdering(DEFAULT_ID, orderingItem);
        var newCountOrderingItems = getCountOrderingItemsByOrderingId(DEFAULT_ID);
        //then
        assertThat(oldCountOrderingItems + 1).isEqualTo(newCountOrderingItems);
    }

    @Test
    void testChangeItemCount() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            //given
            var oldCount = 123;
            var newCount = 321;

            //when
            orderingService.changeItemCount(DEFAULT_ID, newCount);
            var prepareStatement = connection.prepareStatement("select item_count from ordering_items where id = ?");
            prepareStatement.setLong(1, DEFAULT_ID);
            var itemCountFromDb = JdbcUtils.getLongValue(prepareStatement.executeQuery());
            prepareStatement.close();

            //then
            assertThat(newCount).isEqualTo(itemCountFromDb);
            assertThat(itemCountFromDb).isNotEqualTo(oldCount);
        }
    }

    @Test
    void testGetOrdering() {
        //given
        var generatedOrdering = testDataGenerator.generateOrdering();
        var orderingItemList = testDataGenerator.generateOrderingItemList(3);
        generatedOrdering.setOrderingItems(orderingItemList);
        Optional<Ordering> savedOrdering = orderingService.saveOrUpdateOrdering(generatedOrdering);

        //when
        var ordering = orderingService.getOrdering(savedOrdering.get().getId());

        //then
        assertThat(ordering).isPresent();
        assertThat(generatedOrdering.getUserName()).isEqualTo(ordering.get().getUserName());
        for (int i = 0; i < ordering.get().getOrderingItems().size(); i++) {
            var orderingItem = orderingItemList.get(i);
            var generatedOrderingItem = generatedOrdering.getOrderingItems().get(i);
            assertThat(generatedOrderingItem.getOrdering().getId()).isEqualTo(orderingItem.getOrdering().getId());
            assertThat(generatedOrderingItem.getItemName()).isEqualTo(orderingItem.getItemName());
            assertThat(generatedOrderingItem.getItemCount()).isEqualTo(orderingItem.getItemCount());
            assertThat(generatedOrderingItem.getItemPrice()).isEqualTo(orderingItem.getItemPrice());
        }
    }

    @Test
    void testSetAllOrderingDone() throws SQLException {
        try (var connection = dataSource.getConnection()) {
            //when
            orderingService.setAllOrderingDone();
            try (var statement = connection.createStatement();
                 var resultSet = statement.executeQuery("select count(id) from ordering where done=false")) {
                resultSet.next();

                //then
                assertThat(resultSet.getLong(1)).isZero();
            }
        }
    }

    private long getCountOrdering() throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select count(id) from ordering")) {
            resultSet.next();
            return resultSet.getLong(1);
        }
    }

    private long getCountOrderingItems() throws SQLException {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select count(id) from ordering_items")) {
            resultSet.next();
            return resultSet.getLong(1);
        }
    }

    private long getCountOrderingItemsByOrderingId(long orderingId) throws SQLException {
        try (var connection = dataSource.getConnection();
             var ps = connection.prepareStatement("select count(id) from ordering_items where ordering_id = ?")) {
            ps.setLong(1, orderingId);
            return JdbcUtils.getLongValue(ps.executeQuery());
        }
    }


}
