package com.blinov.agileengine.services;

import com.blinov.agileengine.cache.AccountBalanceManager;
import com.blinov.agileengine.cache.TransactionCacheManager;
import com.blinov.agileengine.converter.TransactionConverter;
import com.blinov.agileengine.model.Transaction;
import com.blinov.agileengine.model.enums.TransactionType;
import com.blinov.agileengine.request.TransactionRequest;
import com.blinov.agileengine.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.blinov.agileengine.error.TransactionError.AMOUNT_NOT_ACCEPTABLE_ERROR;

@Service
public class DebitService extends BaseService {

    @Autowired private TransactionCacheManager cacheManager;
    @Autowired private AccountBalanceManager balanceManager;
    @Autowired private TransactionConverter converter;

    public BaseResponse makeTransaction(TransactionRequest request) {
        Transaction transaction = converter.createTransactionFromRequest(request);
        return makeTransaction(transaction);
    }

    public BaseResponse makeTransaction(Transaction transaction) {
        BaseResponse response = new BaseResponse();
        Long amount = transaction.getAmount();
        if (balanceManager.decreaseBalance(amount)) {
            cacheManager.saveTransaction(transaction);
        } else {
            setTransactionError(response, AMOUNT_NOT_ACCEPTABLE_ERROR);
        }
        return response;
    }

    public BaseResponse retrieveTransaction(Transaction transaction) {
        Long amount = transaction.getAmount();
        TransactionType oppositeType = TransactionType.getOppositeType(transaction.getType());
        Transaction newTransaction = converter.createTransactionFromRequest(amount, oppositeType);
        return makeTransaction(newTransaction);
    }
}
