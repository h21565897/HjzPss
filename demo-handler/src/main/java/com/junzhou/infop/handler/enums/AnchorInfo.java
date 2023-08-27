package com.junzhou.infop.handler.enums;


import com.junzhou.infop.enums.AnchorState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnchorInfo {


    private SimpleTaskInfo simpleTaskInfo;
    /**
     * @see com.junzhou.infop.enums.AnchorState
     */
    private AnchorState state;

    private long logTimestamp;
}
