package com.paylater.payment_service;

import com.paylater.payment_service.exception.TransactionFailedException;
import com.paylater.user_service.exception.InternalException;

public interface IPlatformPaymentService {
    void newTransaction(String userName, String merchantName, Double transactionAmount) throws TransactionFailedException;

    void payBack(String userName, Double payBackAmount) throws TransactionFailedException;

    String getAllUserTransactions(String userName) throws InternalException;
}
