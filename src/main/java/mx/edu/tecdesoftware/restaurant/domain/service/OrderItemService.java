package mx.edu.tecdesoftware.restaurant.domain.service;

import mx.edu.tecdesoftware.restaurant.domain.OrderItemDomain;
import mx.edu.tecdesoftware.restaurant.persistence.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItemDomain> getAll() {
        return orderItemRepository.getAll();
    }

    public Optional<OrderItemDomain> getOrderItem(Integer orderId, Integer productId) {
        return orderItemRepository.getById(orderId, productId);
    }

    public List<OrderItemDomain> getByOrderId(Integer orderId) {
        return orderItemRepository.getByOrderId(orderId);
    }

    public OrderItemDomain save(OrderItemDomain orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public boolean delete(Integer orderId, Integer productId) {
        return getOrderItem(orderId, productId).map(item -> {
            orderItemRepository.delete(orderId, productId);
            return true;
        }).orElse(false);
    }
}