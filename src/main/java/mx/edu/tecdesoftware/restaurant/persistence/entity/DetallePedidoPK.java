package mx.edu.tecdesoftware.restaurant.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetallePedidoPK implements Serializable {

    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "id_platillo")
    private Integer idPlatillo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedidoPK that = (DetallePedidoPK) o;
        return Objects.equals(idPedido, that.idPedido) && Objects.equals(idPlatillo, that.idPlatillo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, idPlatillo);
    }

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public Integer getIdPlatillo() { return idPlatillo; }
    public void setIdPlatillo(Integer idPlatillo) { this.idPlatillo = idPlatillo; }
}