package com.blinov.agileengine.response;

import com.blinov.agileengine.model.Transaction;
import lombok.Data;

@Data
public class TransactionResponse extends BaseResponse {

    private Transaction transaction;

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "transaction=" + transaction +
                ", statusCode=" + statusCode +
                ", errorText='" + errorText + '\'' +
                '}';
    }
}
