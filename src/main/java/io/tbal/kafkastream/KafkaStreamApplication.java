package io.tbal.kafkastream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
public class KafkaStreamApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamApplication.class, args);
    }
}
