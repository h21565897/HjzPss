package com.junzhou.infop.handler.receiver.kafka;

import cn.hutool.core.util.StrUtil;
import com.junzhou.infop.handler.utils.GroupIdMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListenerAnnotationBeanPostProcessor;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@Service
public class ReceiverConfigs {
    private static final String RECEIVER_METHOD_NAME = "Receiver.consumer";
    private static List<String> groupIds = GroupIdMappingUtils.getAllGroupIds();
    private static Integer index = 0;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private ConsumerFactory consumerFactory;

    @Bean
    public static KafkaListenerAnnotationBeanPostProcessor.AnnotationEnhancer groupIdEnhancer() {
        return (attrs, element) -> {
            if (element instanceof Method) {
                String name = ((Method) element).getDeclaringClass().getSimpleName() + StrUtil.DOT + ((Method) element).getName();
                if (Objects.equals(name, RECEIVER_METHOD_NAME)) {
                    attrs.put("groupId", groupIds.get(index++));
                }
            }
            return attrs;
        };

    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < groupIds.size(); i++) {
            context.getBean(Receiver.class);
        }
    }
}
