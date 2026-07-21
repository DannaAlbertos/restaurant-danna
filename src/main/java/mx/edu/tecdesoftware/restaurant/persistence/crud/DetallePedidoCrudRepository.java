package mx.edu.tecdesoftware.restaurant.persistence.crud;

import mx.edu.tecdesoftware.restaurant.persistence.entity.DetallePedido;
import mx.edu.tecdesoftware.restaurant.persistence.entity.DetallePedidoPK;
import org.springframework.data.repository.CrudRepository;

public interface DetallePedidoCrudRepository extends CrudRepository<DetallePedido, DetallePedidoPK> {
}