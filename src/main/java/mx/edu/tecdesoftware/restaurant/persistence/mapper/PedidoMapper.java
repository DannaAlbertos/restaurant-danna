package mx.edu.tecdesoftware.restaurant.persistence.mapper;

import mx.edu.tecdesoftware.restaurant.domain.PedidoDomain;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Pedido; // Tu entidad JPA
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "idMesa", target = "numeroMesa")
    @Mapping(source = "fecha", target = "fechaPedido")
    @Mapping(source = "estado", target = "estadoActual")
    PedidoDomain toDomain(Pedido entity);

    @Mapping(source = "numeroMesa", target = "idMesa")
    @Mapping(source = "fechaPedido", target = "fecha")
    @Mapping(source = "estadoActual", target = "estado")
    Pedido toEntity(PedidoDomain domain);
}