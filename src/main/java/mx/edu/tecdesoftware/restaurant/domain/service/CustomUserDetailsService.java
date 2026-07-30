package mx.edu.tecdesoftware.restaurant.domain.service;

import mx.edu.tecdesoftware.restaurant.persistence.crud.ClienteCrudRepository;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Cliente;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClienteCrudRepository clienteCrudRepository;

    public CustomUserDetailsService(ClienteCrudRepository clienteCrudRepository) {
        this.clienteCrudRepository = clienteCrudRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteCrudRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        return new User(cliente.getCorreoElectronico(), cliente.getContrasena(), new ArrayList<>());
    }
}