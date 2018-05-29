package com.blinov.agileengine.converter;

import com.blinov.agileengine.model.Transaction;
import com.blinov.agileengine.model.enums.TransactionType;
import com.blinov.agileengine.request.TransactionRequest;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class TransactionConverter {

    public Transaction createTransactionFromRequest(TransactionRequest request) {
        Transaction transaction = new Transaction();
        transaction.setId(request.getId());
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setEffectiveDate(request.getEffectiveDate());
        return transaction;
    }

    public Transaction createTransactionFromRequest(Long amount, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString().split("-")[0]);
        transaction.setType(type);
        transaction.setAmount(amount);
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date());
        transaction.setEffectiveDate(date);
        return transaction;
    }
}
