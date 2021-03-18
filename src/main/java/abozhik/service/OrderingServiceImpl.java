package abozhik.service;

import abozhik.model.Ordering;
import abozhik.model.OrderingItem;
import abozhik.repository.OrderingItemsRepository;
import abozhik.repository.OrderingRepository;
import abozhik.sessionmanager.SessionManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderingServiceImpl implements OrderingService {

    private static final Logger logger = LoggerFactory.getLogger(OrderingServiceImpl.class);

    private final OrderingRepository orderingRepository;
    private final OrderingItemsRepository orderingItemsRepository;

    public Optional<Ordering> saveOrUpdateOrdering(Ordering ordering) {
        try (SessionManager sessionManager = orderingRepository.getSessionManager()) {
            sessionManager.beginSession();
            try {
                for (var orderingItem : ordering.getOrderingItems()) {
                    orderingItem.setOrdering(ordering);
                }
                Optional<Ordering> resultOrdering = orderingRepository.saveOrdering(ordering);
                sessionManager.commitSession();
                return resultOrdering;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
        }
        return Optional.empty();
    }

    public void addItemToOrdering(Long orderingId, OrderingItem orderingItem) {
        try (SessionManager sessionManager = orderingItemsRepository.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Ordering ordering = new Ordering();
                ordering.setId(orderingId);
                orderingItem.setOrdering(ordering);
                orderingItemsRepository.saveOrderingItem(orderingItem);
                sessionManager.commitSession();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
        }
    }

    public void changeItemCount(Long orderingItemId, Integer itemCount) {
        try (SessionManager sessionManager = orderingItemsRepository.getSessionManager()) {
            sessionManager.beginSession();
            try {
                orderingItemsRepository.updateItemCount(orderingItemId, itemCount);
                sessionManager.commitSession();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
        }
    }

    public Optional<Ordering> getOrdering(Long orderingId) {
        try (SessionManager sessionManager = orderingRepository.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<Ordering> orderingWithItems = orderingRepository.getOrderingWithItems(orderingId);
                sessionManager.commitSession();
                return orderingWithItems;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
        }
        return Optional.empty();
    }

    public void setAllOrderingDone() {
        try (SessionManager sessionManager = orderingRepository.getSessionManager()) {
            sessionManager.beginSession();
            try {
                orderingRepository.setAllOrderingDone();
                sessionManager.commitSession();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
        }
    }

}
