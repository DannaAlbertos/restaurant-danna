package mx.edu.tecdesoftware.restaurant.persistence;

import mx.edu.tecdesoftware.restaurant.domain.DishDomain;
import mx.edu.tecdesoftware.restaurant.persistence.crud.PlatilloCrudRepository;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Platillo;
import mx.edu.tecdesoftware.restaurant.persistence.mapper.DishMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DishRepository {
    private final PlatilloCrudRepository platilloCrudRepository;
    private final DishMapper dishMapper;

    public DishRepository(PlatilloCrudRepository platilloCrudRepository, DishMapper dishMapper) {
        this.platilloCrudRepository = platilloCrudRepository;
        this.dishMapper = dishMapper;
    }

    public List<DishDomain> getAll() {
        return dishMapper.toDomains(platilloCrudRepository.findAll());
    }

    public List<DishDomain> getByCategory(Integer categoryId) {
        return dishMapper.toDomains(platilloCrudRepository.findByIdCategoria(categoryId));
    }

    public Optional<DishDomain> getDish(Integer dishId) {
        return platilloCrudRepository.findById(dishId).map(dishMapper::toDomain);
    }

    public DishDomain save(DishDomain dish) {
        Platillo platillo = dishMapper.toEntity(dish);
        return dishMapper.toDomain(platilloCrudRepository.save(platillo));
    }
}