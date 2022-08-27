package com.demo.kafkaproxy.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

@Service
public class KafkaSenderService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaSenderService.class);

    // 这里也可以选择构造器注入
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic){
        String payLoad = "{\"entityId\":\"7b07b0fa-4fba-4165-ba3f-5f291c07e837\",\"userId\":{\"orgName\":\"elive-b2c\",\"orgId\":\"43b538ae-215f-4fee-89a3-472430020527\"},\"passedAt\":\"2019-07-21T12:05:00Z\",\"course\":{\"courseId\":\"7b07b0fa-4fba-4165-ba3f-5f291c07e837\",\"version\":0},\"lessonPlan\":{\"lessonPlanId\":\"92e0bb2f-ef6e-4ddc-a400-42934ec61b30\",\"version\":0,\"instructionsLocale\":\"string\"}}";
        //如果kafka topic不存在会自动创建
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, "1", payLoad);

        record.headers().add(new RecordHeader("studentId", "9e872e86-ca7c-4927-a3b1-b6895716a8f4".getBytes()));
        record.headers().add(new RecordHeader("x-schema-name", "enrollment/coursePassed".getBytes()));
        record.headers().add(new RecordHeader("x-schema-version", "1.0.0".getBytes()));

        // 这个知识点值得记下来
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(1660112265000L);

        record.headers().add(new RecordHeader("x-timestamp",
                buffer.array()));

        kafkaTemplate.send(record);
        System.out.println("send message successfully");
    }
}
