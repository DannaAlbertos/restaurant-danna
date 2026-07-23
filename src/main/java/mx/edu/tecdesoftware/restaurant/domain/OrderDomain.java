package mx.edu.tecdesoftware.restaurant.domain;

import java.time.LocalDateTime;

public class OrderDomain {
    private Integer orderId;
    private Integer tableNumber;
    private String customerId;
    private LocalDateTime orderDate;
    private String status;
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