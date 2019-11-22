package io.tbal.kafkastream.kafka.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import io.tbal.kafkastream.kafka.producer.AvroProducer;
import reactor.core.publisher.Mono;

/**
 * Created by vince.chae on 2019/11/22 6:44 오후
 */
@Configuration
public class AvroRouter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvroRouter.class);

    private final AvroProducer avroProducer;

    public AvroRouter(AvroProducer avroProducer) {this.avroProducer = avroProducer;}

    @Bean
    public RouterFunction<ServerResponse> test() {
        return route(GET("/test"), request -> ok().body(Mono.just("RESULT!"), String.class));
    }

    @Bean
    public RouterFunction<ServerResponse> producerAvroMessage() {
        return route(POST("/employees/{id}/{firstName}/{lastName}"), request -> Mono.just(request).doOnNext(req -> {
            avroProducer.produceEmployeeDetails(Integer.valueOf(req.pathVariable("id")), req.pathVariable("firstName"), req.pathVariable("lastName"));
        }).then(ok().build()));
    }
}
