package io.tbal.kafkastream.kafka.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by vince.chae on 2019/11/22 6:31 오후
 */
public interface EmployeeProcessor extends Source, Sink {
    String INPUT = "employee-in";
    String OUTPUT = "employee-out";
    @Input(INPUT)
    SubscribableChannel input();
    @Output(OUTPUT)
    MessageChannel output();
}
