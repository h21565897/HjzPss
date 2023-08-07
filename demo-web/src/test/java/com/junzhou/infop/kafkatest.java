package com.junzhou.infop;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class kafkatest {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @Test
    public void test1(){
        kafkaTemplate.send("austinBusiness","qqqqq");
    }
}
