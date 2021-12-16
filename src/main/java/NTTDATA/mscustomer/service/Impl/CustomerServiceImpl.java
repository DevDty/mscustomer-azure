package NTTDATA.mscustomer.service.Impl;

import NTTDATA.mscustomer.model.Customer;
import NTTDATA.mscustomer.model.CustomerType;
import NTTDATA.mscustomer.model.CustomerTypeTwo;
import NTTDATA.mscustomer.repository.CustomerRepository;
import NTTDATA.mscustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private  final CustomerRepository repository;

    @Override
    public Mono<Customer> save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Mono<Customer> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Customer> findByDni(String dni) {
        return repository.findByDni(dni);
    }

    @Override
    public Flux<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Void> delete(Customer customer) {
        return repository.delete(customer);
    }

}
