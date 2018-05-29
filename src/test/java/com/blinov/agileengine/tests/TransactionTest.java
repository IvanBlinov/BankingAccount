package com.blinov.agileengine.tests;

import com.blinov.agileengine.controller.TransactionController;
import com.blinov.agileengine.model.enums.TransactionType;
import com.blinov.agileengine.request.TransactionRequest;
import com.blinov.agileengine.response.BaseResponse;
import com.blinov.agileengine.response.TransactionResponse;
import com.blinov.agileengine.response.TransactionsListResponse;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.blinov.agileengine.error.TransactionError.*;
import static com.blinov.agileengine.model.enums.TransactionType.credit;
import static com.blinov.agileengine.model.enums.TransactionType.debit;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionTest {

    @Autowired private TransactionController controller;

    private static final String VALID_ID = "12345678";

    @Test
    public void test1_getEmptyTransactionListTest() {
        TransactionsListResponse response = controller.getTransactions();
        assertTrue(response.getTransactionList().size() == 0);
    }

    @Test
    public void test2_falseAddTransactionTest() {
        TransactionRequest request = createTransactionRequest(VALID_ID, debit, 30L, "2018-05-25T12:06:51.507Z");
        BaseResponse response = controller.createNewTransaction(request);
        assertTrue(response.getStatusCode() == AMOUNT_NOT_ACCEPTABLE_ERROR.getCode());
        assertTrue(response.getErrorText().equals(AMOUNT_NOT_ACCEPTABLE_ERROR.getText()));

        request = createTransactionRequest(VALID_ID, debit, -30L, "2018-05-25T12:06:51.507Z");
        response = controller.createNewTransaction(request);
        assertTrue(response.getStatusCode() == BAD_REQUEST_ERROR.getCode());
        assertTrue(response.getErrorText().equals(BAD_REQUEST_ERROR.getText()));

        request = createTransactionRequest(VALID_ID, credit, -30L, "2018-05-25T12:06:51.507Z");
        response = controller.createNewTransaction(request);
        assertTrue(response.getStatusCode() == BAD_REQUEST_ERROR.getCode());
        assertTrue(response.getErrorText().equals(BAD_REQUEST_ERROR.getText()));
    }

    @Test
    public void test3_trueAddTransactionTest() {
        TransactionRequest request = createTransactionRequest("12345677", credit, 30L, "2018-05-25T12:06:51.507Z");
        BaseResponse response = controller.createNewTransaction(request);
        assertTrue(response.getStatusCode() == 200);

        request = createTransactionRequest(VALID_ID, debit, 30L, "2018-05-25T12:06:51.507Z");
        response = controller.createNewTransaction(request);
        assertTrue(response.getStatusCode() == 200);
    }

    @Test
    public void test4_getTwoTransactionsTest() {
        TransactionsListResponse response = controller.getTransactions();
        assertTrue(response.getTransactionList().size() == 2);
    }

    @Test
    public void test5_addTransactionWithExistedIdTest() {
        TransactionRequest request = createTransactionRequest(VALID_ID, credit, 30L, "2018-05-25T12:06:51.507Z");
        BaseResponse response = controller.createNewTransaction(request);
        assertTrue(response.getStatusCode() == INVALID_ID_ERROR.getCode());
        assertTrue(response.getErrorText().equals(INVALID_ID_ERROR.getText()));
    }

    @Test
    public void test6_withdrawMoreMoneyThenHave() {
        TransactionRequest request = createTransactionRequest("12345679", debit, 30000L, "2018-05-25T12:06:51.507Z");
        BaseResponse response = controller.createNewTransaction(request);
        assertTrue(response.getStatusCode() == AMOUNT_NOT_ACCEPTABLE_ERROR.getCode());
        assertTrue(response.getErrorText().equals(AMOUNT_NOT_ACCEPTABLE_ERROR.getText()));
    }

    @Test
    public void test7_getTransactionById() {
        String requestedId = VALID_ID;
        TransactionResponse response = controller.getTransactionById(requestedId);
        assertTrue(response.getTransaction().getId().equals(requestedId));

        requestedId = "123496586787";
        response = controller.getTransactionById(requestedId);
        assertTrue(response.getStatusCode() == TRANSACTION_NOT_FOUNT_ERROR.getCode());
        assertTrue(response.getErrorText().equals(TRANSACTION_NOT_FOUNT_ERROR.getText()));
    }

    @Test
    public void test8_deleteTransactionsTest() {
        BaseResponse response = controller.deleteTransaction("56789943634");
        assertTrue(response.getStatusCode() == TRANSACTION_NOT_FOUNT_ERROR.getCode());
        assertTrue(response.getErrorText().equals(TRANSACTION_NOT_FOUNT_ERROR.getText()));

        response = controller.deleteTransaction(VALID_ID);
        assertTrue(response.getStatusCode() == 200);
    }

    private TransactionRequest createTransactionRequest(String id, TransactionType type, Long amount, String date) {
        TransactionRequest request = new TransactionRequest();
        request.setId(id);
        request.setType(type);
        request.setAmount(amount);
        request.setEffectiveDate(date);
        return request;
    }
}
