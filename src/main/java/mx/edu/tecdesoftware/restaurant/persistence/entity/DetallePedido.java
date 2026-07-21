package mx.edu.tecdesoftware.restaurant.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalles_pedidos")
public class DetallePedido {

    @EmbeddedId
    private DetallePedidoPK id;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "estado")
    private Boolean estado;

    @ManyToOne
    @MapsId("idPedido")
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    private Pedido pedido;

    public DetallePedidoPK getId() { return id; }
    public void setId(DetallePedidoPK id) { this.id = id; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
}