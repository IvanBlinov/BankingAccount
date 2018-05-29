package com.blinov.agileengine.services;

import com.blinov.agileengine.cache.TransactionCacheManager;
import com.blinov.agileengine.model.Transaction;
import com.blinov.agileengine.request.TransactionRequest;
import com.blinov.agileengine.response.BaseResponse;
import com.blinov.agileengine.response.TransactionResponse;
import com.blinov.agileengine.response.TransactionsListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.blinov.agileengine.error.TransactionError.BAD_REQUEST_ERROR;
import static com.blinov.agileengine.error.TransactionError.INVALID_ID_ERROR;
import static com.blinov.agileengine.error.TransactionError.TRANSACTION_NOT_FOUNT_ERROR;


@Service
public class TransactionsService extends BaseService {

    @Autowired private CreditService creditService;
    @Autowired private DebitService debitService;
    @Autowired private TransactionCacheManager cacheManager;

    public TransactionsListResponse getAllTransactions() {
        TransactionsListResponse response = new TransactionsListResponse();
        List<Transaction> transactions = cacheManager.getTransactions();
        if (transactions == null) {
            setTransactionError(response, TRANSACTION_NOT_FOUNT_ERROR);
        } else {
            response.setTransactionList(transactions);
        }
        return response;
    }

    public TransactionResponse getTransactionById(String transactionId) {
        TransactionResponse response = new TransactionResponse();
        Transaction transaction = cacheManager.getTransactionById(transactionId);
        if (transaction == null) {
            setTransactionError(response, TRANSACTION_NOT_FOUNT_ERROR);
        } else {
            response.setTransaction(transaction);
        }
        return response;
    }

    public BaseResponse createNewTransaction (TransactionRequest request) {
        BaseResponse response = new BaseResponse();
        if (request.getAmount() < 0) {
            setTransactionError(response, BAD_REQUEST_ERROR);
            return response;
        } else if (cacheManager.getTransactionById(request.getId()) != null) {
            setTransactionError(response, INVALID_ID_ERROR);
            return response;
        } else {
            switch (request.getType()) {
                case debit:
                    return debitService.makeTransaction(request);
                case credit:
                    return creditService.makeTransaction(request);
                default: {
                    setTransactionError(response, BAD_REQUEST_ERROR);
                    return response;
                }
            }
        }
    }

    public BaseResponse deleteTransactionById(String transactionId) {
        BaseResponse response = new BaseResponse();
        Transaction transaction = cacheManager.getTransactionById(transactionId);
        if (transaction == null) {
            setTransactionError(response, TRANSACTION_NOT_FOUNT_ERROR);
            return response;
        } else {
            switch (transaction.getType()) {
                case debit:
                    return creditService.retrieveTransaction(transaction);
                case credit:
                    return debitService.retrieveTransaction(transaction);
                default: {
                    setTransactionError(response, BAD_REQUEST_ERROR);
                    return response;
                }
            }
        }
    }

}
