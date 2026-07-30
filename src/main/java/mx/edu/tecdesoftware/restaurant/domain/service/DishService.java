package mx.edu.tecdesoftware.restaurant.domain.service;

import mx.edu.tecdesoftware.restaurant.domain.DishDomain;
import mx.edu.tecdesoftware.restaurant.persistence.DishRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public List<DishDomain> getAll() { return dishRepository.getAll(); }
    public List<DishDomain> getByCategory(Integer categoryId) { return dishRepository.getByCategory(categoryId); }
    public Optional<DishDomain> getDish(Integer dishId) { return dishRepository.getDish(dishId); }
    public DishDomain save(DishDomain dish) { return dishRepository.save(dish); }
}