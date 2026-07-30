package mx.edu.tecdesoftware.restaurant.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.restaurant.domain.DishDomain;
import mx.edu.tecdesoftware.restaurant.domain.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
@Tag(name = "Dish", description = "Manage dishes in the restaurant menu")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    @Operation(
            summary = "Get all dishes",
            description = "Return a list of all available dishes in the restaurant menu"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of dishes")
    @ApiResponse(responseCode = "204", description = "No dishes content found")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<DishDomain>> getAll() {
        List<DishDomain> dishes = dishService.getAll();
        return dishes.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get dishes by category ID",
            description = "Return a list of dishes belonging to a specific category"
    )
    @ApiResponse(responseCode = "200", description = "Dishes found in category")
    @ApiResponse(responseCode = "204", description = "No dishes found for this category")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<DishDomain>> getByCategory(
            @Parameter(description = "ID of the category to filter dishes", example = "1", required = true)
            @PathVariable("categoryId") Integer categoryId) {
        List<DishDomain> dishes = dishService.getByCategory(categoryId);
        return dishes.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(dishes, HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Save a new dish",
            description = "Register a new dish in the menu and return the created entity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example dish",
                                    value = """
                                            {
                                              "name": "Tacos al Pastor",
                                              "description": "Orden de 5 tacos con piña, cilantro y cebolla",
                                              "price": 120.50,
                                              "categoryId": 1,
                                              "available": true
                                            }
                                           """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Dish created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid dish data")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid token")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "409", description = "Dish conflict - Duplicate dish name or code")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<DishDomain> save(@RequestBody DishDomain dish) {
        return new ResponseEntity<>(dishService.save(dish), HttpStatus.CREATED);
    }
}