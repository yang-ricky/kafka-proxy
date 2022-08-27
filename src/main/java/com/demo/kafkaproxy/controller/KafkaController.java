package com.demo.kafkaproxy.controller;

import com.demo.kafkaproxy.service.KafkaSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KafkaController {

    @Autowired
    private KafkaSenderService kafkaSenderService;

    @GetMapping("/kafka")
    public String sendMsg() {
        kafkaSenderService.send("prism.raw.catalyst.elive");
        return "send message";
    }
}
