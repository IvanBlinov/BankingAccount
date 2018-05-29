package com.blinov.agileengine.cache;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AccountBalanceManager {

    private Long balance;

    @PostConstruct
    private void init() {
        this.balance = 0L;
    }

    public synchronized boolean increaseBalance(Long amount) {
        if (amount >= 0) {
            this.balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean decreaseBalance(Long amount) {
        if (amount < 0 || balance - amount < 0) {
            return false;
        } else {
            this.balance -= amount;
            return true;
        }
    }
}
