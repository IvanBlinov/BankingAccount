package com.blinov.agileengine.cache;


import com.blinov.agileengine.model.Transaction;
import com.blinov.agileengine.model.enums.TransactionType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionCacheManager {

    private List<Transaction> transactions;

    @PostConstruct
    private void init() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsByType(TransactionType type) {
        return transactions.stream()
                .filter(transaction -> transaction.getType().equals(type))
                .collect(Collectors.toList());
    }

    public Transaction getTransactionById(String transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId)) {
                return transaction;
            }
        }
        return null;
    }

    public void saveTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
