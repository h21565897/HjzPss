package com.junzhou.infop.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataTemplateParams {
    private Integer page;

    private Integer perPage;

    private Long id;

    private String msgContent;
}
