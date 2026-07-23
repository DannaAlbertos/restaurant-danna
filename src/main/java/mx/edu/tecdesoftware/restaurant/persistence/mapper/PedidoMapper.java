package mx.edu.tecdesoftware.restaurant.persistence.mapper;

import mx.edu.tecdesoftware.restaurant.domain.PedidoDomain;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Pedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMapper {

    @Mapping(source = "idMesa", target = "numeroMesa")
    @Mapping(source = "fecha", target = "fechaPedido")
    @Mapping(source = "estado", target = "estadoActual")
    PedidoDomain toDomain(Pedido entity);

    List<PedidoDomain> toDomains(List<Pedido> pedidos);

    @InheritInverseConfiguration
    Pedido toEntity(PedidoDomain domain);
}