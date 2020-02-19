package com.ws.learn.rpc.service;

import com.ws.learn.api.response.CommonResponse;

public interface TestService {
    CommonResponse<Long> getARandomNumber(Integer param);
    CommonResponse<Long> reset();
}
