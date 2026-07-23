package mx.edu.tecdesoftware.restaurant.persistence.mapper;

import mx.edu.tecdesoftware.restaurant.domain.CustomerDomain;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    @Mapping(source = "idCliente", target = "customerId")
    @Mapping(source = "nombre", target = "firstName")
    @Mapping(source = "apellidos", target = "lastName")
    @Mapping(source = "celular", target = "phone")
    @Mapping(source = "correoElectronico", target = "email")
    CustomerDomain toDomain(Cliente entity);

    List<CustomerDomain> toDomains(List<Cliente> customers);

    @InheritInverseConfiguration
    Cliente toEntity(CustomerDomain domain);
}