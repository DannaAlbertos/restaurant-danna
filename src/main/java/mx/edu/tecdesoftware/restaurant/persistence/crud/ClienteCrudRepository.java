package mx.edu.tecdesoftware.restaurant.persistence.crud;

import mx.edu.tecdesoftware.restaurant.persistence.entity.Cliente;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ClienteCrudRepository extends ListCrudRepository<Cliente, Integer> {
    Optional<Cliente> findByCorreoElectronico(String correoElectronico);

    boolean existsByCorreoElectronico(String correoElectronico);
}