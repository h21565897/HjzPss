package com.junzhou.infop.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaConfig {
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("thing1")
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }
}
