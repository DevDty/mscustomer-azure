package NTTDATA.mscustomer.handler;

import NTTDATA.mscustomer.model.Customer;
import NTTDATA.mscustomer.model.CustomerType;
import NTTDATA.mscustomer.model.CustomerTypeTwo;
import NTTDATA.mscustomer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CustomerHandler {

    private final CustomerService service;


    public Mono<ServerResponse> create(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        return customerMono
                .flatMap(service::save)
                .flatMap(r -> ServerResponse.created(URI.create("/customer/".concat(r.getId())))
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(r)
                );
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        return service.findById(id)
                .flatMap(r -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(r)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByDni(ServerRequest request){
        String dni = request.pathVariable("dni");
        return service.findByDni(dni)
                .flatMap(r -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(r)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Customer.class);
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        String id = request.pathVariable("id");
        Mono<Customer> customerMonoDb = service.findById(id);
        return customerMonoDb.zipWith(customerMono, (db, req) ->{
            db.setDni(req.getDni());
            db.setName(req.getName());
            db.setLastname(req.getLastname());
            db.setEmail(req.getEmail());
            db.setAddress(req.getAddress());
            db.setPhone(req.getPhone());
            db.setCustomerType(req.getCustomerType());
            return db;
        }).flatMap(p -> ServerResponse.created(URI.create("/customer/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.save(p), Customer.class))
        .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request){
        String id = request.pathVariable("id");
        Mono<Customer> customerMonoDb = service.findById(id);
        return customerMonoDb.flatMap(p -> service.delete(p).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
