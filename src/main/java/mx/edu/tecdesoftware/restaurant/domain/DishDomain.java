package mx.edu.tecdesoftware.restaurant.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public class DishDomain {
    @Schema(description = "ID autogenerado del platillo", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer dishId;

    @Schema(description = "Nombre del platillo", example = "Tacos al Pastor")
    private String name;

    @Schema(description = "Precio unitario", example = "95.50")
    private Double price;

    @Schema(description = "Disponibilidad del platillo", example = "true")
    private Boolean active;

    @Schema(description = "ID de la categoría a la que pertenece", example = "1")
    private Integer categoryId;

    @Schema(description = "Nombre de la categoría (Solo lectura)", example = "Platillos Fuertes", accessMode = Schema.AccessMode.READ_ONLY)
    private String categoryName;

    // Getters y Setters
    public Integer getDishId() { return dishId; }
    public void setDishId(Integer dishId) { this.dishId = dishId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}