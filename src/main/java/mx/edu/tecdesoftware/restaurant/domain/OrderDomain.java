package mx.edu.tecdesoftware.restaurant.domain;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

public class OrderDomain {
    @Schema(description = "ID autogenerado del pedido", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer orderId;

    @Schema(description = "Número de la mesa asignada", example = "1")
    private Integer tableNumber;

    @Schema(description = "ID numérico del cliente", example = "1")
    private String customerId;

    @Schema(description = "Fecha y hora del pedido", example = "2026-07-28T14:00:00")
    private LocalDateTime orderDate;

    @Schema(description = "Estado actual del pedido", example = "PENDIENTE")
    private String status;

    @Schema(description = "Comentarios adicionales sobre el pedido", example = "Sin cebolla por favor")
    private String comment;

    public OrderDomain() {
    }

    public OrderDomain(Integer orderId, Integer tableNumber, String customerId, LocalDateTime orderDate, String status, String comment) {
        this.orderId = orderId;
        this.tableNumber = tableNumber;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.comment = comment;
    }

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Integer getTableNumber() { return tableNumber; }
    public void setTableNumber(Integer tableNumber) { this.tableNumber = tableNumber; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}