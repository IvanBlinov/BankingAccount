package com.blinov.agileengine.controller;

import com.blinov.agileengine.request.TransactionRequest;
import com.blinov.agileengine.response.BaseResponse;
import com.blinov.agileengine.response.TransactionResponse;
import com.blinov.agileengine.response.TransactionsListResponse;
import com.blinov.agileengine.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.blinov.agileengine.utils.ControllerPaths.TRANSACTIONS_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = TRANSACTIONS_PATH, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired private TransactionsService transactionsService;

    @RequestMapping(method = RequestMethod.GET)
    public TransactionsListResponse getTransactions() {
        return transactionsService.getAllTransactions();
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
    public TransactionResponse getTransactionById(@PathVariable String transactionId) {
        return transactionsService.getTransactionById(transactionId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse createNewTransaction(@RequestBody TransactionRequest request) {
        return transactionsService.createNewTransaction(request);
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
    public BaseResponse deleteTransaction(@PathVariable String transactionId) {
        return transactionsService.deleteTransactionById(transactionId);
    }
}
