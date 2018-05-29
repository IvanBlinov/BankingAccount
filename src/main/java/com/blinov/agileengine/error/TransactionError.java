package com.blinov.agileengine.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionError {


    BAD_REQUEST_ERROR               (400, "Bad Request"),
    INVALID_ID_ERROR                (400, "Invalid ID supplied"),
    TRANSACTION_NOT_FOUNT_ERROR     (404, "Transaction not found"),
    AMOUNT_NOT_ACCEPTABLE_ERROR     (406, "Money amount not acceptable");

    private int code;
    private String text;

    public static String getErrorText(int errorCode) {
        for (TransactionError error : TransactionError.values()) {
            if (error.getCode() == errorCode) {
                return error.getText();
            }
        }
        return null;
    }
}
