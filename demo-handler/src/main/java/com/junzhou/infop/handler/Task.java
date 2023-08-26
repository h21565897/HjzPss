package com.junzhou.infop.handler;

import com.junzhou.infop.handler.handler.HandlerRouter;
import com.junzhou.infop.pipeline.domain.TaskInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class Task implements Runnable {


    @Autowired
    HandlerRouter handlerRouter;

    private TaskInfo taskinfo;

    @Override
    public void run() {
        handlerRouter.route(taskinfo.getSendChannelId()).doHandle(taskinfo);
    }
}
