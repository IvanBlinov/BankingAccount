package com.blinov.agileengine.model.enums;

public enum TransactionType {

    credit,
    debit;

    public static TransactionType getOppositeType(TransactionType type) {
        if (type.equals(credit)) {
            return debit;
        }
        return credit;
    }
}
