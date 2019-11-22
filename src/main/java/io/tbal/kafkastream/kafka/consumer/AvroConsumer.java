package io.tbal.kafkastream.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import io.tbal.kafkastream.avro.Employee;
import io.tbal.kafkastream.kafka.processor.EmployeeProcessor;

/**
 * Created by vince.chae on 2019/11/22 6:31 오후
 */
@Service
public class AvroConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvroConsumer.class);

    @StreamListener(EmployeeProcessor.INPUT)
    public void consumeEmployeeDetails(Employee employeeDetails) {
        LOGGER.info("Let's process employee details: {}", employeeDetails);
    }

}
