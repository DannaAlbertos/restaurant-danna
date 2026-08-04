package mx.edu.tecdesoftware.restaurant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SecurityScheme(
        name = "miAutenticacion",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(
                title = "API Rest Restaurant",
                version = "1.0.0",
                description = "### Sistema de Gestión de Restaurantes\n" +
                        "API RESTful para la administración de menús, clientes y pedidos.\n\n" +
                        "**Instrucciones de Autenticación:**\n" +
                        "1. Genera tu token en el endpoint `/auth/login`.\n" +
                        "2. Haz clic en el botón verde **Authorize** ubicado arriba a la derecha.\n" +
                        "3. Ingresa tu token JWT en 'miAutenticación'."

        ),
        security = @SecurityRequirement(name = "miAutenticacion")
)
@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }

}