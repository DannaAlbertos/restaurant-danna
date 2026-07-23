package mx.edu.tecdesoftware.restaurant.persistence.mapper;

import mx.edu.tecdesoftware.restaurant.domain.ClienteDomain;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    @Mapping(source = "celular", target = "telefono")
    @Mapping(source = "correoElectronico", target = "correo")
    ClienteDomain toDomain(Cliente entity);

    List<ClienteDomain> toDomains(List<Cliente> clientes);

    @InheritInverseConfiguration
    Cliente toEntity(ClienteDomain domain);
}