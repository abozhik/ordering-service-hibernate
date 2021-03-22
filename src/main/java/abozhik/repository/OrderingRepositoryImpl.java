package abozhik.repository;

import abozhik.model.Ordering;
import abozhik.sessionmanager.DatabaseSessionHibernate;
import abozhik.sessionmanager.SessionManagerHibernate;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderingRepositoryImpl implements OrderingRepository {

    private final SessionManagerHibernate sessionManager;

    public Optional<Ordering> saveOrdering(Ordering ordering) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        currentSession.getSession().persist(ordering);
        return Optional.ofNullable(ordering);
    }

    public void setAllOrderingDone() {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        Query<?> query = currentSession.getSession().createQuery("update Ordering o set o.done=true");
        query.executeUpdate();
    }

    public Optional<Ordering> getOrderingWithItems(long id) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        Query<Ordering> query = currentSession.getSession().createQuery("select o from Ordering o left join fetch o.orderingItems where o.id=:id", Ordering.class);
        query.setParameter("id", id);
        Ordering result = query.uniqueResult();
        return Optional.ofNullable(result);
    }

}
