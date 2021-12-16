package NTTDATA.mscustomer.config;

import NTTDATA.mscustomer.handler.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(CustomerHandler handler){
        return route(GET("/customer/{id}"), handler::findById)
                .andRoute(POST("/customer"), handler::create)
                .andRoute(GET("/customer"), handler::findAll)
                .andRoute(PUT("/customer/{id}"), handler::update)
                .andRoute(DELETE("/customer/{id}"), handler::delete)
                .andRoute(GET("/"), handler::findAll)
                .andRoute(GET("/customer/dni/{dni}"), handler::findByDni);
    }
}
