package mx.edu.tecdesoftware.restaurant.persistence.mapper;

import mx.edu.tecdesoftware.restaurant.domain.DishDomain;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Platillo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper {

    @Mapping(source = "idPlatillo", target = "dishId")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "estado", target = "active")
    @Mapping(source = "idCategoria", target = "categoryId")
    @Mapping(source = "categoria.descripcion", target = "categoryName")
    DishDomain toDomain(Platillo entity);

    List<DishDomain> toDomains(List<Platillo> entities);

    @InheritInverseConfiguration
    @Mapping(target = "categoria", ignore = true)
    Platillo toEntity(DishDomain domain);


}