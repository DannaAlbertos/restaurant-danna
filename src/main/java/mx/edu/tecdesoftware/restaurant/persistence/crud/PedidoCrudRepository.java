package mx.edu.tecdesoftware.restaurant.persistence.crud;

import mx.edu.tecdesoftware.restaurant.persistence.entity.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoCrudRepository extends CrudRepository<Pedido, Integer> {
}