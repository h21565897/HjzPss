package com.junzhou.infop.service.api.service;

import com.junzhou.infop.service.api.domain.SendRequest;
import com.junzhou.infop.vo.BasicResultVo;

public interface SendService {

    BasicResultVo send(SendRequest sendRequest);


}
