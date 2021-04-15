package com.paylater.payment_service.model;

import java.util.Date;
import java.util.UUID;

public class Transaction {
    private final String id;
    private final String userName;
    private final Double amount;
    private final Date createdAt;
    private final String status;
    private String merchantName;

    public Transaction(String userName, Double amount, String status) {
        this.userName = userName;
        this.amount = amount;
        this.status = status;
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
    }

    public Transaction(String userName, Double amount, String status, String merchantName) {
        this(userName, amount, status);
        this.merchantName = merchantName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getMerchantName() {
        return merchantName;
    }
}
