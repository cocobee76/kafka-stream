package io.tbal.kafkastream.kafka.producer;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import io.tbal.kafkastream.avro.Employee;
import io.tbal.kafkastream.avro.EmployeeKey;
import io.tbal.kafkastream.kafka.processor.EmployeeProcessor;

/**
 * Created by vince.chae on 2019/11/22 6:39 오후
 */
@Component
public class AvroProducer {

    private final EmployeeProcessor processor;

    public AvroProducer(EmployeeProcessor processor) {this.processor = processor;}

    public void produceEmployeeDetails(int empId, String firstName, String lastName) {

        // creating employee details
        Employee employee = Employee.newBuilder()
                                    .setId(empId)
                                    .setFirstName(firstName)
                                    .setDepartment("IT")
                                    .setDesignation("Engineer")
                                    .setLastName(lastName)
                                    .build();

        // creating partition key for kafka topic
        EmployeeKey employeeKey = EmployeeKey.newBuilder()
                                             .setId(empId)
                                             .setDepartmentName("IT")
                                             .build();

        Message<Employee> message = MessageBuilder.withPayload(employee)
                                                  .setHeader(KafkaHeaders.MESSAGE_KEY, employeeKey)
                                                  .build();

        processor.output().send(message);
    }
}
