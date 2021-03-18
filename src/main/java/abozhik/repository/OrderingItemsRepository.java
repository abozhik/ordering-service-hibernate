package abozhik.repository;

import abozhik.model.OrderingItem;
import abozhik.sessionmanager.SessionManager;

public interface OrderingItemsRepository {

    OrderingItem saveOrderingItem(OrderingItem orderingItem);

    void updateItemCount(Long orderingItemId, Integer itemCount);

    SessionManager getSessionManager();

}
