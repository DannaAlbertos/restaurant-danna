package mx.edu.tecdesoftware.restaurant.domain;

import java.time.LocalDateTime;

public class PedidoDomain {
    private Integer idPedido;
    private Integer numeroMesa; // Difiere de 'idMesa' en la entidad
    private String idCliente;
    private LocalDateTime fechaPedido; // Difiere de 'fecha'
    private String estadoActual; // Difiere de 'estado'
    private String comentario;

    // Getters y Setters
    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public Integer getNumeroMesa() { return numeroMesa; }
    public void setNumeroMesa(Integer numeroMesa) { this.numeroMesa = numeroMesa; }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public LocalDateTime getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDateTime fechaPedido) { this.fechaPedido = fechaPedido; }

    public String getEstadoActual() { return estadoActual; }
    public void setEstadoActual(String estadoActual) { this.estadoActual = estadoActual; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}