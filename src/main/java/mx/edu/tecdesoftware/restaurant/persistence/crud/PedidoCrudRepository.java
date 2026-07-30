package mx.edu.tecdesoftware.restaurant.persistence.crud;

import mx.edu.tecdesoftware.restaurant.persistence.entity.Pedido;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

public interface PedidoCrudRepository extends ListCrudRepository<Pedido, Integer> {
    List<Pedido> findByIdCliente(Integer idCliente);
    List<Pedido> findByEstado(String estado);
}