package mx.edu.tecdesoftware.restaurant.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.restaurant.domain.CustomerDomain;
import mx.edu.tecdesoftware.restaurant.domain.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer", description = "Manage customers in the restaurant")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @Operation(
            summary = "Get all customers",
            description = "Return a list of all registered customers in the system"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of customers")
    @ApiResponse(responseCode = "204", description = "No customers content found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<CustomerDomain>> getAll() {
        List<CustomerDomain> customers = customerService.getAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get customer by ID",
            description = "Return a customer by its ID if it exists"
    )
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<CustomerDomain> getCustomer(
            @Parameter(description = "ID of the customer retrieved", example = "10", required = true)
            @PathVariable("id") Integer customerId) {
        return customerService.getCustomer(customerId)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/email/{email}")
    @Operation(
            summary = "Get customer by email",
            description = "Return a customer by its email address if it exists"
    )
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<CustomerDomain> getByEmail(
            @Parameter(description = "Email of the customer to search", example = "carlos.mendoza@email.com", required = true)
            @PathVariable("email") String email) {

        return customerService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Save a new customer",
            description = "Register a new customer and return the created customer entity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example customer",
                                    value = """
                                            {
                                              "firstName": "Carlos",
                                              "lastName": "Mendoza",
                                              "email": "carlos.mendoza@email.com",
                                              "phone": "5551234567"
                                            }
                                           """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid customer data")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid token")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "409", description = "Customer conflict - Duplicate email")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<CustomerDomain> save(@RequestBody CustomerDomain customer) {
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a customer by ID",
            description = "Delete a customer record from the system by its ID"
    )
    @ApiResponse(responseCode = "200", description = "Customer deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the customer to be deleted", example = "10", required = true)
            @PathVariable("id") Integer customerId) {
        if (customerService.delete(customerId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}