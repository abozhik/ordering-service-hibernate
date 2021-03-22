package abozhik.repository;

import abozhik.model.OrderingItem;
import abozhik.sessionmanager.DatabaseSessionHibernate;
import abozhik.sessionmanager.SessionManagerHibernate;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;

@RequiredArgsConstructor
public class OrderingItemsRepositoryImpl implements OrderingItemsRepository {

    private final SessionManagerHibernate sessionManager;

    public OrderingItem saveOrderingItem(OrderingItem orderingItem) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        currentSession.getSession().saveOrUpdate(orderingItem);
        return orderingItem;
    }

    public void updateItemCount(Long orderingItemId, Integer itemCount) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        Query<?> query = currentSession.getSession()
                .createQuery("update OrderingItem oi set oi.itemCount=:itemCount where oi.id=:id");
        query.setParameter("itemCount", itemCount);
        query.setParameter("id", orderingItemId);
        query.executeUpdate();
    }

}
