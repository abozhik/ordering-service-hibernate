package abozhik.sessionmanager;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Getter
@Setter
public class DatabaseSessionHibernate implements DatabaseSession {
    private final Session session;
    private final Transaction transaction;

    DatabaseSessionHibernate(Session session) {
        this.session = session;
        this.transaction = session.beginTransaction();
    }

    public void close() {
        if (transaction.isActive()) {
            transaction.commit();
        }
        session.close();
    }
}
