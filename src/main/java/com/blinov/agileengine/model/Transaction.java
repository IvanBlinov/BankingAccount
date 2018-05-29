package com.blinov.agileengine.model;

import com.blinov.agileengine.model.enums.TransactionType;
import lombok.Data;

@Data
public class Transaction {

    private String id;
    private TransactionType type;
    private Long amount;
    private String effectiveDate;
}
