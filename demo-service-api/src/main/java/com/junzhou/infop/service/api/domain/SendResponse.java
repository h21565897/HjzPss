package com.junzhou.infop.service.api.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendResponse {

    private String code;

    private String msg;

}
