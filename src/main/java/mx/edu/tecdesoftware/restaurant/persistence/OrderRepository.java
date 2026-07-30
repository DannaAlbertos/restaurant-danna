package mx.edu.tecdesoftware.restaurant.persistence;

import mx.edu.tecdesoftware.restaurant.domain.OrderDomain;
import mx.edu.tecdesoftware.restaurant.persistence.crud.PedidoCrudRepository;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Pedido;
import mx.edu.tecdesoftware.restaurant.persistence.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    private final PedidoCrudRepository pedidoCrudRepository;
    private final OrderMapper orderMapper;

    public OrderRepository(PedidoCrudRepository pedidoCrudRepository, OrderMapper orderMapper) {
        this.pedidoCrudRepository = pedidoCrudRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderDomain> getAll() {
        List<Pedido> pedidos = pedidoCrudRepository.findAll();
        return orderMapper.toDomains(pedidos);
    }

    public Optional<OrderDomain> getById(Integer orderId) {
        return pedidoCrudRepository.findById(orderId)
                .map(orderMapper::toDomain);
    }

    public List<OrderDomain> getByCustomerId(Integer customerId) {
        List<Pedido> pedidos = pedidoCrudRepository.findByIdCliente(customerId);
        return orderMapper.toDomains(pedidos);
    }

    public List<OrderDomain> getByStatus(String status) {
        List<Pedido> pedidos = pedidoCrudRepository.findByEstado(status);
        return orderMapper.toDomains(pedidos);
    }

    public OrderDomain save(OrderDomain orderDomain) {
        Pedido pedido = orderMapper.toEntity(orderDomain);
        return orderMapper.toDomain(pedidoCrudRepository.save(pedido));
    }

    public void delete(Integer orderId) {
        pedidoCrudRepository.deleteById(orderId);
    }

    public boolean exists(Integer orderId) {
        return pedidoCrudRepository.existsById(orderId);
    }
}