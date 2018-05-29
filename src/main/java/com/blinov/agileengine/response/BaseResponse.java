package com.blinov.agileengine.response;

import lombok.Data;

@Data
public class BaseResponse {

    protected int statusCode;
    protected String errorText;

    public BaseResponse() {
        this.setStatusCode(200);
    }
}
