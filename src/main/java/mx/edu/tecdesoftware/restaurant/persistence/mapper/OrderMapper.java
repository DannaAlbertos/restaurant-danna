package mx.edu.tecdesoftware.restaurant.persistence.mapper;

import mx.edu.tecdesoftware.restaurant.domain.OrderDomain;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Pedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "idPedido", target = "orderId")
    @Mapping(source = "idMesa", target = "tableNumber")
    @Mapping(source = "idCliente", target = "customerId")
    @Mapping(source = "fecha", target = "orderDate")
    @Mapping(source = "estado", target = "status")
    @Mapping(source = "comentario", target = "comment")
    OrderDomain toDomain(Pedido entity);

    List<OrderDomain> toDomains(List<Pedido> orders);

    @InheritInverseConfiguration
    Pedido toEntity(OrderDomain domain);
}