package mx.edu.tecdesoftware.restaurant.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.restaurant.domain.OrderDomain;
import mx.edu.tecdesoftware.restaurant.domain.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order", description = "Manage customer orders in the restaurant")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(
            summary = "Get all orders",
            description = "Return a list of all orders registered in the system"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of orders")
    @ApiResponse(responseCode = "204", description = "No orders content found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<OrderDomain>> getAll() {
        List<OrderDomain> orders = orderService.getAll();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get order by ID",
            description = "Return an order by its ID if it exists"
    )
    @ApiResponse(responseCode = "200", description = "Order found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<OrderDomain> getOrder(
            @Parameter(description = "ID of the order retrieved", example = "1", required = true)
            @PathVariable("id") Integer orderId) {
        return orderService.getOrder(orderId)
                .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer/{customerId}")
    @Operation(
            summary = "Get orders by customer ID",
            description = "Return all orders associated with a specific customer"
    )
    @ApiResponse(responseCode = "200", description = "Orders found for the specified customer")
    @ApiResponse(responseCode = "204", description = "No orders found for this customer")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<OrderDomain>> getByCustomerId(
            @Parameter(description = "ID of the customer", example = "10", required = true)
            @PathVariable("customerId") Integer customerId) {
        List<OrderDomain> orders = orderService.getByCustomerId(customerId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    @Operation(
            summary = "Get orders by status",
            description = "Return a list of orders filtered by status (e.g., PENDING, COMPLETED, CANCELLED)"
    )
    @ApiResponse(responseCode = "200", description = "Orders found with specified status")
    @ApiResponse(responseCode = "204", description = "No orders found with this status")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<OrderDomain>> getByStatus(
            @Parameter(description = "Status of the orders to filter", example = "PENDING", required = true)
            @PathVariable("status") String status) {
        List<OrderDomain> orders = orderService.getByStatus(status);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Save a new order",
            description = "Register a new order and return the created order entity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example order",
                                    value = """
                                            {
                                              "customerId": 10,
                                              "date": "2026-03-30T14:30:00",
                                              "status": "PENDING",
                                              "total": 241.00,
                                              "items": [
                                                {
                                                  "dishId": 1,
                                                  "quantity": 2,
                                                  "price": 120.50
                                                }
                                              ]
                                            }
                                           """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Order created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid order data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<OrderDomain> save(@RequestBody OrderDomain order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an order by ID",
            description = "Delete an order record from the system by its ID"
    )
    @ApiResponse(responseCode = "200", description = "Order deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid token")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the order to be deleted", example = "1", required = true)
            @PathVariable("id") Integer orderId) {
        if (orderService.delete(orderId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}