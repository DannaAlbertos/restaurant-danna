package mx.edu.tecdesoftware.restaurant.domain.service;

import mx.edu.tecdesoftware.restaurant.domain.dto.AuthResponse;
import mx.edu.tecdesoftware.restaurant.domain.dto.LoginRequest;
import mx.edu.tecdesoftware.restaurant.persistence.crud.ClienteCrudRepository;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Cliente;
import mx.edu.tecdesoftware.restaurant.web.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final ClienteCrudRepository clienteCrudRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(ClienteCrudRepository clienteCrudRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.clienteCrudRepository = clienteCrudRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<AuthResponse> login(LoginRequest loginRequest) {
        System.out.println("=== DIAGNÓSTICO DE LOGIN ===");
        System.out.println("1. Email recibido en Request: [" + loginRequest.getEmail() + "]");
        System.out.println("2. Password recibida en Request: [" + loginRequest.getPassword() + "]");

        Optional<Cliente> clienteOpt = clienteCrudRepository.findByCorreoElectronico(loginRequest.getEmail());

        if (clienteOpt.isEmpty()) {
            System.out.println(" ERROR: El usuario NO existe en PostgreSQL con ese correo.");
            return Optional.empty();
        }

        Cliente cliente = clienteOpt.get();
        System.out.println("3. Hash encontrado en la BD: " + cliente.getContrasena());

        boolean coincide = passwordEncoder.matches(loginRequest.getPassword(), cliente.getContrasena());
        System.out.println("4. ¿La contraseña coincide con el Hash?: " + coincide);

        if (coincide) {
            String token = jwtUtil.generateToken(cliente.getCorreoElectronico());
            System.out.println(" TOKEN GENERADO CON ÉXITO");
            return Optional.of(new AuthResponse(token));
        } else {
            System.out.println(" ERROR: La contraseña '1234' NO coincide con el hash guardado en PostgreSQL.");
            return Optional.empty();
        }
    }
}