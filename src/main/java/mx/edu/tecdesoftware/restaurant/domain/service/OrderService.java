package mx.edu.tecdesoftware.restaurant.domain.service;

import mx.edu.tecdesoftware.restaurant.domain.OrderDomain;
import mx.edu.tecdesoftware.restaurant.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDomain> getAll() {
        return orderRepository.getAll();
    }

    public Optional<OrderDomain> getOrder(Integer orderId) {
        return orderRepository.getById(orderId);
    }

    public List<OrderDomain> getByCustomerId(Integer customerId) {
        return orderRepository.getByCustomerId(customerId);
    }

    public List<OrderDomain> getByStatus(String status) {
        return orderRepository.getByStatus(status);
    }

    public OrderDomain save(OrderDomain order) {
        return orderRepository.save(order);
    }

    public boolean delete(Integer orderId) {
        return getOrder(orderId).map(order -> {
            orderRepository.delete(orderId);
            return true;
        }).orElse(false);
    }
}