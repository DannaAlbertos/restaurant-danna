package mx.edu.tecdesoftware.restaurant.persistence;

import mx.edu.tecdesoftware.restaurant.domain.CustomerDomain;
import mx.edu.tecdesoftware.restaurant.persistence.crud.ClienteCrudRepository;
import mx.edu.tecdesoftware.restaurant.persistence.entity.Cliente;
import mx.edu.tecdesoftware.restaurant.persistence.mapper.CustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final ClienteCrudRepository clienteCrudRepository;
    private final CustomerMapper customerMapper;

    public CustomerRepository(ClienteCrudRepository clienteCrudRepository, CustomerMapper customerMapper) {
        this.clienteCrudRepository = clienteCrudRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDomain> getAll() {
        List<Cliente> clientes = clienteCrudRepository.findAll();
        return customerMapper.toDomains(clientes);
    }

    public Optional<CustomerDomain> getById(Integer customerId) {
        return clienteCrudRepository.findById(customerId)
                .map(customerMapper::toDomain);
    }

    public Optional<CustomerDomain> getByEmail(String email) {
        return clienteCrudRepository.findByCorreoElectronico(email)
                .map(cliente -> customerMapper.toDomain(cliente));
    }

    public CustomerDomain save(CustomerDomain customerDomain) {
        if (customerDomain.getCustomerId() == null &&
                clienteCrudRepository.existsByCorreoElectronico(customerDomain.getEmail())) {

            throw new IllegalArgumentException("El correo electrónico '" + customerDomain.getEmail() + "' ya está registrado.");
        }

        if (customerDomain.getCustomerId() != null) {
            clienteCrudRepository.findByCorreoElectronico(customerDomain.getEmail())
                    .ifPresent(clienteExistente -> {
                        if (!clienteExistente.getIdCliente().equals(customerDomain.getCustomerId())) {
                            throw new IllegalArgumentException("El correo electrónico ya pertenece a otro cliente.");
                        }
                    });
        }

        Cliente cliente = customerMapper.toEntity(customerDomain);
        return customerMapper.toDomain(clienteCrudRepository.save(cliente));
    }

    public void delete(Integer customerId) {
        clienteCrudRepository.deleteById(customerId);
    }

    public boolean exists(Integer customerId) {
        return clienteCrudRepository.existsById(customerId);
    }
}