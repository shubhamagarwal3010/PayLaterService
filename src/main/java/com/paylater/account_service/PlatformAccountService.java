package com.paylater.account_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paylater.account_service.exception.AccountAlreadyExists;
import com.paylater.account_service.model.Account;
import com.paylater.user_service.exception.InternalException;
import com.paylater.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlatformAccountService implements IPlatformAccountService {
    private final Map<String, Account> accountMap = new HashMap<>();

    @Override
    public void createAccount(String userName, Double creditLimit) throws AccountAlreadyExists {
        if (!accountMap.containsKey(userName)) {
            accountMap.put(userName, new Account(userName, creditLimit));
        } else {
            throw new AccountAlreadyExists();
        }
    }

    @Override
    public void updateTransaction(String userName, Double transactionAmount) {
        Account account = accountMap.get(userName);
        account.setDues(account.getDues() + transactionAmount);
        accountMap.put(userName, account);
    }

    @Override
    public void payBack(String userName, Double payBackAmount) {
        Account account = accountMap.get(userName);
        account.setDues(account.getDues() - payBackAmount);
        accountMap.put(userName, account);
    }

    @Override
    public Double reportDues(String userName) {
        return Math.abs(accountMap.get(userName).getDues());
    }

    @Override
    public List<String> reportUsersAtCreditLimit() {
        return accountMap.values().stream().filter(x -> Math.abs(x.getDues()) >= x.getCreditLimit()).map(Account::getUserName).collect(Collectors.toList());
    }

    @Override
    public Map<String, Double> reportTotalDues() {
        Map<String, Double> map = new HashMap<>();
        accountMap.forEach((k, v) -> map.put(k, v.getDues()));
        return map;
    }

    @Override
    public String getAccountDetails(String userName) throws InternalException {
        try {
            return JsonUtil.objectToJson(accountMap.get(userName));
        } catch (JsonProcessingException e) {
            throw new InternalException("Internal Server error during data parsing");
        }
    }
}
