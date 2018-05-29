package com.blinov.agileengine.services;

import com.blinov.agileengine.error.TransactionError;
import com.blinov.agileengine.response.BaseResponse;

public class BaseService {

    protected void setTransactionError(BaseResponse response, TransactionError transactionError) {
        response.setErrorText(transactionError.getText());
        response.setStatusCode(transactionError.getCode());
    }
}
