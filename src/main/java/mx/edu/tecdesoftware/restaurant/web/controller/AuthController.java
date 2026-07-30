package mx.edu.tecdesoftware.restaurant.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.restaurant.domain.dto.AuthResponse;
import mx.edu.tecdesoftware.restaurant.domain.dto.LoginRequest;
import mx.edu.tecdesoftware.restaurant.domain.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoint for user login and JWT token generation")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(
            summary = "User login",
            description = "Authenticate user credentials and return a JWT bearer token",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example login credentials",
                                    value = """
                                            {
                                              "email": "carlos.mendoza@email.com",
                                              "password": "1234"
                                            }
                                           """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Successfully authenticated and JWT token generated")
    @ApiResponse(responseCode = "400", description = "Invalid request payload format")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid email or password")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}