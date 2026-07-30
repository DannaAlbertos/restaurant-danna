package mx.edu.tecdesoftware.restaurant.persistence;

import mx.edu.tecdesoftware.restaurant.domain.OrderItemDomain;
import mx.edu.tecdesoftware.restaurant.persistence.crud.DetallePedidoCrudRepository;
import mx.edu.tecdesoftware.restaurant.persistence.entity.DetallePedido;
import mx.edu.tecdesoftware.restaurant.persistence.entity.DetallePedidoPK;
import mx.edu.tecdesoftware.restaurant.persistence.mapper.OrderItemMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemRepository {

    private final DetallePedidoCrudRepository detallePedidoCrudRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemRepository(DetallePedidoCrudRepository detallePedidoCrudRepository, OrderItemMapper orderItemMapper) {
        this.detallePedidoCrudRepository = detallePedidoCrudRepository;
        this.orderItemMapper = orderItemMapper;
    }

    public List<OrderItemDomain> getAll() {
        List<DetallePedido> detalles = detallePedidoCrudRepository.findAll();
        return orderItemMapper.toDomains(detalles);
    }

    public Optional<OrderItemDomain> getById(Integer orderId, Integer productId) {
        DetallePedidoPK pk = new DetallePedidoPK();
        pk.setIdPedido(orderId);
        pk.setIdPlatillo(productId);
        return detallePedidoCrudRepository.findById(pk)
                .map(orderItemMapper::toDomain);
    }

    public List<OrderItemDomain> getByOrderId(Integer orderId) {
        List<DetallePedido> detalles = detallePedidoCrudRepository.findById_IdPedido(orderId);
        return orderItemMapper.toDomains(detalles);
    }

    public OrderItemDomain save(OrderItemDomain orderItem) {
        DetallePedido detalle = orderItemMapper.toEntity(orderItem);
        return orderItemMapper.toDomain(detallePedidoCrudRepository.save(detalle));
    }

    public void delete(Integer orderId, Integer productId) {
        DetallePedidoPK pk = new DetallePedidoPK();
        pk.setIdPedido(orderId);
        pk.setIdPlatillo(productId);
        detallePedidoCrudRepository.deleteById(pk);
    }
}