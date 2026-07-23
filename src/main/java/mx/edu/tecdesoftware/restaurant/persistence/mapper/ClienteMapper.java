package mx.edu.tecdesoftware.restaurant.persistence.mapper;

import mx.edu.tecdesoftware.restaurant.domain.ClienteDomain;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Cliente; // Tu entidad JPA
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "celular", target = "telefono")
    @Mapping(source = "correoElectronico", target = "correo")
    ClienteDomain toDomain(Cliente entity);

    @Mapping(source = "telefono", target = "celular")
    @Mapping(source = "correo", target = "correoElectronico")
    Cliente toEntity(ClienteDomain domain);
}