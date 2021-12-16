package NTTDATA.mscustomer.repository;

import NTTDATA.mscustomer.model.Customer;
import NTTDATA.mscustomer.model.CustomerType;
import NTTDATA.mscustomer.model.CustomerTypeTwo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
    Mono<Customer> save(Customer customer);
    Mono<Customer> findById(String id);
    Mono<Customer> findByDni(String dni);
    Flux<Customer> findAll();
    Mono<Void> delete(Customer customer);
}
