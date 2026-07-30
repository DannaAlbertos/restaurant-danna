package mx.edu.tecdesoftware.restaurant.domain.service;

import mx.edu.tecdesoftware.restaurant.domain.CustomerDomain;
import mx.edu.tecdesoftware.restaurant.persistence.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDomain> getAll() {
        return customerRepository.getAll();
    }

    public Optional<CustomerDomain> getCustomer(Integer customerId) {
        return customerRepository.getById(customerId);
    }

    public Optional<CustomerDomain> getByEmail(String email) {
        return customerRepository.getByEmail(email);
    }

    public CustomerDomain save(CustomerDomain customer) {
        return customerRepository.save(customer);
    }

    public boolean delete(Integer customerId) {
        return getCustomer(customerId).map(customer -> {
            customerRepository.delete(customerId);
            return true;
        }).orElse(false);
    }
}