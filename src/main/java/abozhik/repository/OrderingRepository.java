package abozhik.repository;

import abozhik.model.Ordering;

import java.util.Optional;

public interface OrderingRepository {

    Optional<Ordering> saveOrdering(Ordering ordering);

    void setAllOrderingDone();

    Optional<Ordering> getOrderingWithItems(long orderingId);

}
