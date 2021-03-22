package abozhik.service;

import abozhik.generator.TestDataGenerator;
import abozhik.model.Ordering;
import abozhik.model.OrderingItem;
import abozhik.repository.OrderingItemsRepositoryImpl;
import abozhik.repository.OrderingRepositoryImpl;
import abozhik.sessionmanager.DatabaseSessionHibernate;
import abozhik.sessionmanager.SessionManagerHibernate;
import abozhik.util.HibernateUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.math.RoundingMode;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OrderingServiceTest {

    private static final PostgreSQLContainer<PostgreSQLContainerShared> POSTGRE_SQL_CONTAINER = PostgreSQLContainerShared.getInstance();
    public static final long DEFAULT_ID = 1L;

    static {
        POSTGRE_SQL_CONTAINER.start();
    }

    private final OrderingService orderingService;
    private final TestDataGenerator testDataGenerator;
    private final SessionManagerHibernate sessionManager;

    public OrderingServiceTest() {
        Configuration configuration = HibernateUtils.getConfiguration(
                POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                POSTGRE_SQL_CONTAINER.getUsername(),
                POSTGRE_SQL_CONTAINER.getPassword());
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory(configuration, Ordering.class, OrderingItem.class);
        sessionManager = new SessionManagerHibernate(sessionFactory);
        var orderingItemsRepository = new OrderingItemsRepositoryImpl(sessionManager);
        var orderingRepository = new OrderingRepositoryImpl(sessionManager);
        orderingService = new OrderingServiceImpl(orderingRepository, orderingItemsRepository, sessionManager);
        testDataGenerator = new TestDataGenerator();
    }

    @Test
    void testCreateOrdering() {
        //given
        var oldCountOrdering = getCountOrdering();
        var oldCountOrderingItems = getCountOrderingItems();

        //when
        var orderingUserName = testDataGenerator.getRandomString();
        var orderingItemList = testDataGenerator.generateOrderingItemList(3);
        var generatedOrdering = new Ordering(orderingUserName, false, orderingItemList);
        var ordering = orderingService.saveOrUpdateOrdering(generatedOrdering);

        //then
        var newCountOrdering = getCountOrdering();
        var newCountOrderingItems = getCountOrderingItems();
        assertThat(ordering).isPresent();
        assertThat(getCountOrderingItemsByOrderingId(ordering.get().getId())).isEqualTo(orderingItemList.size());
        assertThat(oldCountOrdering + 1).isEqualTo(newCountOrdering);
        assertThat(oldCountOrderingItems + orderingItemList.size()).isEqualTo(newCountOrderingItems);

        var newOrdering = orderingService.getOrdering(ordering.get().getId());
        assertThat(newOrdering).isPresent();
        Ordering savedOrdering = newOrdering.get();
        assertThat(savedOrdering.getId()).isNotNull();
        assertThat(generatedOrdering.getUserName()).isEqualTo(savedOrdering.getUserName());
        assertThat(generatedOrdering.isDone()).isEqualTo(savedOrdering.isDone());
        for (int i = 0; i < savedOrdering.getOrderingItems().size(); i++) {
            var generatedOrderingItem = generatedOrdering.getOrderingItems().get(i);
            var orderingItem = savedOrdering.getOrderingItems().get(i);
            assertThat(generatedOrderingItem.getItemName()).isEqualTo(orderingItem.getItemName());
            assertThat(generatedOrderingItem.getItemPrice().setScale(2, RoundingMode.CEILING)).isEqualTo(orderingItem.getItemPrice());
            assertThat(generatedOrderingItem.getItemCount()).isEqualTo(orderingItem.getItemCount());
        }
    }

    @Test
    void testAddItemToOrdering() {
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
    void testChangeItemCount() {
        //given
        var oldCount = 123;
        var newCount = 321;

        //when
        orderingService.changeItemCount(DEFAULT_ID, newCount);

        try (sessionManager) {
            sessionManager.beginSession();
            DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
            Query<Integer> query = currentSession.getSession().createQuery("select oi.itemCount from OrderingItem oi where oi.id = :id", Integer.class);
            query.setParameter("id", DEFAULT_ID);
            var itemCountFromDb = query.getSingleResult();
            sessionManager.commitSession();

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
        assertThat(savedOrdering).isPresent();
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
    void testSetAllOrderingDone() {
        //when
        orderingService.setAllOrderingDone();
        try (sessionManager) {
            sessionManager.beginSession();
            DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
            Query<Long> query = currentSession.getSession().createQuery("select count(o.id) from Ordering o where o.done=false", Long.class);
            Long result = query.getSingleResult();
            sessionManager.commitSession();

            //then
            assertThat(result).isZero();
        }
    }

    private long getCountOrdering() {
        try (sessionManager) {
            sessionManager.beginSession();
            DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
            Query<Long> query = currentSession.getSession().createQuery("select count(o.id) from Ordering o", Long.class);
            Long result = query.getSingleResult();
            sessionManager.commitSession();
            return result;
        }
    }

    private long getCountOrderingItems() {
        try (sessionManager) {
            sessionManager.beginSession();
            DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
            Query<Long> query = currentSession.getSession().createQuery("select count(oi.id) from OrderingItem oi", Long.class);
            Long result = query.getSingleResult();
            sessionManager.commitSession();
            return result;
        }
    }

    private long getCountOrderingItemsByOrderingId(long orderingId) {
        try (sessionManager) {
            sessionManager.beginSession();
            DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
            Query<Long> query = currentSession.getSession().createQuery("select count(oi.id) from OrderingItem oi where oi.ordering.id = :id", Long.class);
            query.setParameter("id", orderingId);
            Long result = query.getSingleResult();
            sessionManager.commitSession();
            return result;
        }
    }

}
