package com.blinov.agileengine.response;

import com.blinov.agileengine.model.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class TransactionsListResponse extends BaseResponse {

    private List<Transaction> transactionList;

    @Override
    public String toString() {
        return "TransactionsListResponse{" +
                "transactionList=" + transactionList +
                ", statusCode=" + statusCode +
                ", errorText='" + errorText + '\'' +
                '}';
    }
}
