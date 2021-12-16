package NTTDATA.mscustomer.service;

import NTTDATA.mscustomer.model.Customer;
import NTTDATA.mscustomer.model.CustomerType;
import NTTDATA.mscustomer.model.CustomerTypeTwo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    public Mono<Customer> save(Customer customer);
    public Mono<Customer> findById(String id);
    public Mono<Customer> findByDni(String dni);
    public Flux<Customer> findAll();
    public Mono<Void> delete(Customer customer);
}
