package com.junzhou.infop.pipeline.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailContentModel extends ContentModel {

    private String title;

    private String content;

    private String url;
}
