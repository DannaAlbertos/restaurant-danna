package mx.edu.tecdesoftware.restaurant.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequest {
    @Schema(description = "Correo electrónico del usuario registrado", example = "carlos.mendoza@email.com")
    private String email;

    @Schema(description = "Contraseña en texto plano", example = "1234")
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}