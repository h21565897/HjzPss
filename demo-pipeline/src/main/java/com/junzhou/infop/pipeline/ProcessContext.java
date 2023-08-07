package com.junzhou.infop.pipeline;

import com.junzhou.infop.vo.BasicResultVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProcessContext<T extends ProcessModel> {
    private String code;

    private T processModel;

    private boolean needBreak;

    private BasicResultVo response;

}
