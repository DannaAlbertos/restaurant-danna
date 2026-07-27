package mx.edu.tecdesoftware.restaurant.persistence.crud;

import mx.edu.tecdesoftware.restaurant.persistence.entity.DetallePedido;
import mx.edu.tecdesoftware.restaurant.persistence.entity.DetallePedidoPK;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

public interface DetallePedidoCrudRepository extends ListCrudRepository<DetallePedido, DetallePedidoPK> {
    List<DetallePedido> findById_IdPedido(Integer idPedido);
}