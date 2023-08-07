package com.junzhou.infop.pipeline.config;

import com.junzhou.infop.pipeline.ProcessController;
import com.junzhou.infop.pipeline.ProcessTemplate;
import com.junzhou.infop.pipeline.action.AssembleAction;
import com.junzhou.infop.pipeline.action.PreParamCheckAction;
import com.junzhou.infop.pipeline.action.SendToMqAction;
import com.junzhou.infop.pipeline.enums.BusinessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PipelineConfig {

    @Autowired
    PreParamCheckAction preParamCheckAction;
    @Autowired
    AssembleAction assembleAction;
    @Autowired
    SendToMqAction sendToMqAction;

    @Bean
    public ProcessTemplate commonSendTemplate() {
        ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(Arrays.asList(preParamCheckAction, assembleAction, sendToMqAction));
        return processTemplate;
    }

    @Bean
    public ProcessController processController() {
        ProcessController processController = new ProcessController();
        Map<String, ProcessTemplate> templateConfig = new HashMap<>(4);
        templateConfig.put(BusinessCode.COMMON_SEND.getCode(), commonSendTemplate());
        processController.setTemplateConfig(templateConfig);
        return processController;
    }
}
