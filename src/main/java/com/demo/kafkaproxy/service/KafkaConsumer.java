package com.demo.kafkaproxy.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics={"prism.raw.catalyst.elive"})
    public void listen(ConsumerRecord<?,?> record) {
        Optional
                .ofNullable(record.value())
                .ifPresent(message -> {
                    logger.info(message.toString());
                    System.out.println("consume message successfully");
                });
    }
}
