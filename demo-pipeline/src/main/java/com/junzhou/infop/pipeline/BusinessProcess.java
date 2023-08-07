package com.junzhou.infop.pipeline;

public interface BusinessProcess<T extends ProcessModel> {
    void process(ProcessContext<T> context);
}
