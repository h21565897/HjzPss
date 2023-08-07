package com.junzhou.infop.service.api.service;

import com.junzhou.infop.service.api.domain.SendRequest;
import com.junzhou.infop.service.api.domain.SendResponse;

public interface SendService {

    SendResponse send(SendRequest sendRequest);


}
