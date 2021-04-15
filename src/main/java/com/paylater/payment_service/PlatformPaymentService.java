package com.paylater.payment_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paylater.account_service.IPlatformAccountService;
import com.paylater.merchant_service.IPlatformMerchantService;
import com.paylater.payment_service.exception.TransactionFailedException;
import com.paylater.payment_service.model.Transaction;
import com.paylater.user_service.exception.InternalException;
import com.paylater.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlatformPaymentService implements IPlatformPaymentService {
    private final Map<String, Transaction> transactionMap = new HashMap<>();

    @Autowired
    private IPlatformAccountService accountService;

    @Autowired
    private IPlatformMerchantService merchantService;

    @Override
    public void newTransaction(String userName, String merchantName, Double transactionAmount) throws TransactionFailedException {
        String accountDetails;
        try {
            accountDetails = accountService.getAccountDetails(userName);
        } catch (InternalException e) {
            throw new TransactionFailedException(e.getMessage());
        }
        Double dues = JsonUtil.getDoubleValue(accountDetails, "dues");
        Double creditLimit = JsonUtil.getDoubleValue(accountDetails, "creditLimit");
        if (dues + transactionAmount > creditLimit) {
            throw new TransactionFailedException("rejected! (reason: credit limit)");
        }
        transactionMap.put(userName, new Transaction(userName, transactionAmount, "DEBIT", merchantName));
        accountService.updateTransaction(userName, transactionAmount);
        merchantService.updateTotalDiscount(merchantName, transactionAmount);
    }

    @Override
    public void payBack(String userName, Double payBackAmount) {
        transactionMap.put(userName, new Transaction(userName, payBackAmount, "CREDIT"));
        accountService.payBack(userName, payBackAmount);
    }

    @Override
    public String getAllUserTransactions(String userName) throws InternalException {
        List<Transaction> transactions = transactionMap.values().stream().filter(transaction -> transaction.getUserName().equals(userName)).collect(Collectors.toList());
        try {
            return JsonUtil.objectToJson(transactions);
        } catch (JsonProcessingException e) {
            throw new InternalException("Internal Server error during data parsing");
        }
    }
}
