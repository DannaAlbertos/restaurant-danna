package mx.edu.tecdesoftware.restaurant.persistence.crud;

import mx.edu.tecdesoftware.restaurant.persistence.entity.Platillo;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

public interface PlatilloCrudRepository extends ListCrudRepository<Platillo, Integer> {
    List<Platillo> findByIdCategoria(Integer idCategoria);
}