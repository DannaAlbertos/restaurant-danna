package mx.edu.tecdesoftware.restaurant.domain;

import io.swagger.v3.oas.annotations.media.Schema;

public class CustomerDomain {
    @Schema(description = "ID único del cliente", example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer customerId;

    @Schema(description = "Nombre(s) del cliente", example = "Carlos")
    private String firstName;

    @Schema(description = "Apellido(s) del cliente", example = "Mendoza")
    private String lastName;

    @Schema(description = "Número telefónico", example = "9991234567")
    private String phone;

    @Schema(description = "Correo electrónico", example = "carlos.mendoza@email.com")
    private String email;

    public CustomerDomain() {
    }

    public CustomerDomain(Integer customerId, String firstName, String lastName, String phone, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}