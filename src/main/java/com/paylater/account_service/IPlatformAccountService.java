package com.paylater.account_service;

import com.paylater.account_service.exception.AccountAlreadyExists;
import com.paylater.user_service.exception.InternalException;

import java.util.List;
import java.util.Map;

public interface IPlatformAccountService {
    void createAccount(String userName, Double creditLimit) throws AccountAlreadyExists;

    void updateTransaction(String userName, Double transactionAmount);

    void payBack(String userName, Double payBackAmount);

    Double reportDues(String userName);

    List<String> reportUsersAtCreditLimit();

    Map<String, Double> reportTotalDues();

    String getAccountDetails(String userName) throws InternalException;
}
