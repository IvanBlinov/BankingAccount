package com.blinov.agileengine.request;

import com.blinov.agileengine.model.enums.TransactionType;
import lombok.Data;

@Data
public class TransactionRequest {

    private String id;
    private TransactionType type;
    private Long amount;
    private String effectiveDate;
}
