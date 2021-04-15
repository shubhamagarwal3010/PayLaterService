package com.paylater.account_service.model;

import java.util.Date;

public class Account {
    private final String userName;
    private final Date createdAt;
    private Double dues;
    private Double creditLimit;

    public Account(String userName, Double creditLimit) {
        this.userName = userName;
        this.creditLimit = creditLimit;
        this.createdAt = new Date();
        this.dues = 0.0;
    }

    public String getUserName() {
        return userName;
    }

    public Double getDues() {
        return dues;
    }

    public void setDues(Double dues) {
        this.dues = dues;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
