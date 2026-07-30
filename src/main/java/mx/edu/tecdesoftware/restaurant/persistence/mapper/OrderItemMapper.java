package mx.edu.tecdesoftware.restaurant.persistence.mapper;

import mx.edu.tecdesoftware.restaurant.domain.OrderItemDomain;
import mx.edu.tecdesoftware.restaurant.persistence.entity.DetallePedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemMapper {

    @Mapping(source = "id.idPedido", target = "orderId")
    @Mapping(source = "id.idPlatillo", target = "productId")
    @Mapping(source = "cantidad", target = "quantity")
    @Mapping(source = "subtotal", target = "total")
    @Mapping(source = "estado", target = "status")
    OrderItemDomain toDomain(DetallePedido entity);

    List<OrderItemDomain> toDomains(List<DetallePedido> details);

    @InheritInverseConfiguration
    DetallePedido toEntity(OrderItemDomain domain);
}