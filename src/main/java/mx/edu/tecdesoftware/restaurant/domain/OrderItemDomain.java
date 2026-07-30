package mx.edu.tecdesoftware.restaurant.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public class OrderItemDomain {
    @Schema(description = "ID del pedido existente", example = "1")
    private Integer orderId;

    @Schema(description = "ID del platillo/producto existente", example = "1")
    private Integer productId;

    @Schema(description = "Cantidad solicitada", example = "2")
    private Integer quantity;

    @Schema(description = "Subtotal calculado", example = "150.0")
    private Double total;

    @Schema(description = "Estado del ítem", example = "true")
    private Boolean status;

    public OrderItemDomain() {
    }

    public OrderItemDomain(Integer orderId, Integer productId, Integer quantity, Double total, Boolean status) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
    }

    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}