package mx.edu.tecdesoftware.restaurant.persistence.crud;

import mx.edu.tecdesoftware.restaurant.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteCrudRepository extends CrudRepository<Cliente, String> {
}