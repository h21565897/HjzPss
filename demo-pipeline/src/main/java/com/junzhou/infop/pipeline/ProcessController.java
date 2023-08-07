package com.junzhou.infop.pipeline;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessController {
    private Map<String, ProcessTemplate> templateConfig = null;

    public ProcessContext process(ProcessContext context) {
        List<BusinessProcess> processList = templateConfig.get(context.getCode()).getProcessList();
        for (var businessProcess : processList) {
            businessProcess.process(context);
            if (context.isNeedBreak()) {
                break;
            }
        }
        return context;
    }
}
