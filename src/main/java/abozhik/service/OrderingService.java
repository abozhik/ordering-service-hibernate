package abozhik.service;

import abozhik.model.Ordering;
import abozhik.model.OrderingItem;

import java.sql.SQLException;
import java.util.Optional;

public interface OrderingService {

    Optional<Ordering> saveOrUpdateOrdering(Ordering ordering);

    void addItemToOrdering(Long orderingId, OrderingItem orderingItem);

    void changeItemCount(Long orderingItemId, Integer itemCount);

    Optional<Ordering> getOrdering(Long orderingId);

    void setAllOrderingDone();

}
