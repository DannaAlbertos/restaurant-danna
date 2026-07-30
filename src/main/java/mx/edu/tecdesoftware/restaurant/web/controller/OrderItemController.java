package mx.edu.tecdesoftware.restaurant.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.restaurant.domain.OrderItemDomain;
import mx.edu.tecdesoftware.restaurant.domain.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
@Tag(name = "Order Item", description = "Manage individual items within restaurant orders")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    @Operation(
            summary = "Get all order items",
            description = "Return a list of all order items registered in the system"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of order items")
    @ApiResponse(responseCode = "204", description = "No order items content found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<OrderItemDomain>> getAll() {
        List<OrderItemDomain> items = orderItemService.getAll();
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    @Operation(
            summary = "Get order items by order ID",
            description = "Return a list of items associated with a specific order"
    )
    @ApiResponse(responseCode = "200", description = "Order items found for specified order")
    @ApiResponse(responseCode = "204", description = "No items found for this order")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<OrderItemDomain>> getByOrderId(
            @Parameter(description = "ID of the order to filter items", example = "1", required = true)
            @PathVariable("orderId") Integer orderId) {
        List<OrderItemDomain> items = orderItemService.getByOrderId(orderId);
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{orderId}/{productId}")
    @Operation(
            summary = "Get an order item by composite key",
            description = "Return a specific order item using its order ID and product ID"
    )
    @ApiResponse(responseCode = "200", description = "Order item found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Order item not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<OrderItemDomain> getOrderItem(
            @Parameter(description = "Order ID part of composite key", example = "1", required = true)
            @PathVariable("orderId") Integer orderId,
            @Parameter(description = "Product/Dish ID part of composite key", example = "1", required = true)
            @PathVariable("productId") Integer productId) {
        return orderItemService.getOrderItem(orderId, productId)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(
            summary = "Save a new order item",
            description = "Add an item to an order and return the created entity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example order item",
                                    value = """
                                            {
                                              "orderId": 1,
                                              "productId": 1,
                                              "quantity": 2,
                                              "price": 120.50
                                            }
                                           """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Order item created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid order item data")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid token")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "409", description = "Order item conflict - Duplicate item in order")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<OrderItemDomain> save(@RequestBody OrderItemDomain orderItem) {
        return new ResponseEntity<>(orderItemService.save(orderItem), HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}/{productId}")
    @Operation(
            summary = "Delete an order item by composite key",
            description = "Delete an order item record using its order ID and product ID"
    )
    @ApiResponse(responseCode = "200", description = "Order item deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Order item not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Order ID part of composite key", example = "1", required = true)
            @PathVariable("orderId") Integer orderId,
            @Parameter(description = "Product/Dish ID part of composite key", example = "1", required = true)
            @PathVariable("productId") Integer productId) {
        if (orderItemService.delete(orderId, productId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}