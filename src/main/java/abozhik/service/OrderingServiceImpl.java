package abozhik.service;

import abozhik.model.Ordering;
import abozhik.model.OrderingItem;
import abozhik.repository.OrderingItemsRepository;
import abozhik.repository.OrderingRepository;
import abozhik.sessionmanager.SessionManagerHibernate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class OrderingServiceImpl implements OrderingService {

    private final OrderingRepository orderingRepository;
    private final OrderingItemsRepository orderingItemsRepository;
    private final SessionManagerHibernate sessionManager;

    public Optional<Ordering> saveOrUpdateOrdering(Ordering ordering) {
        try (sessionManager) {
            sessionManager.beginSession();
            try {
                for (var orderingItem : ordering.getOrderingItems()) {
                    orderingItem.setOrdering(ordering);
                }
                Optional<Ordering> resultOrdering = orderingRepository.saveOrdering(ordering);
                sessionManager.commitSession();
                return resultOrdering;
            } catch (Exception e) {
                log.error("Error during saving ordering", e);
                sessionManager.rollbackSession();
            }
        }
        return Optional.empty();
    }

    public void addItemToOrdering(Long orderingId, OrderingItem orderingItem) {
        try (sessionManager) {
            sessionManager.beginSession();
            try {
                Ordering ordering = new Ordering();
                ordering.setId(orderingId);
                orderingItem.setOrdering(ordering);
                orderingItemsRepository.saveOrderingItem(orderingItem);
                sessionManager.commitSession();
            } catch (Exception e) {
                log.error("Error during adding item to ordering", e);
                sessionManager.rollbackSession();
            }
        }
    }

    public void changeItemCount(Long orderingItemId, Integer itemCount) {
        try (sessionManager) {
            sessionManager.beginSession();
            try {
                orderingItemsRepository.updateItemCount(orderingItemId, itemCount);
                sessionManager.commitSession();
            } catch (Exception e) {
                log.error("Error during changing item count in ordering", e);
                sessionManager.rollbackSession();
            }
        }
    }

    public Optional<Ordering> getOrdering(Long orderingId) {
        try (sessionManager) {
            sessionManager.beginSession();
            try {
                Optional<Ordering> orderingWithItems = orderingRepository.getOrderingWithItems(orderingId);
                sessionManager.commitSession();
                return orderingWithItems;
            } catch (Exception e) {
                log.error("Error during getting ordering", e);
                sessionManager.rollbackSession();
            }
        }
        return Optional.empty();
    }

    public void setAllOrderingDone() {
        try (sessionManager) {
            sessionManager.beginSession();
            try {
                orderingRepository.setAllOrderingDone();
                sessionManager.commitSession();
                log.info("All ordering is done");
            } catch (Exception e) {
                log.error("Error during setting all orderings done", e);
                sessionManager.rollbackSession();
            }
        }
    }

}
