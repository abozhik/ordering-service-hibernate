package abozhik.repository;

import abozhik.model.OrderingItem;

public interface OrderingItemsRepository {

    OrderingItem saveOrderingItem(OrderingItem orderingItem);

    void updateItemCount(Long orderingItemId, Integer itemCount);

}
