package com.paylater.merchant_service;

import com.paylater.merchant_service.exception.MerchantAlreadyExists;
import com.paylater.merchant_service.exception.MerchantDataNotValid;
import com.paylater.merchant_service.exception.MerchantNotFound;
import com.paylater.user_service.exception.InternalException;

public interface IPlatformMerchantService {
    void addMerchant(String merchantName, String email, Double discountPercent) throws MerchantAlreadyExists, MerchantDataNotValid;

    void updateMerchant(String merchantName, Double newDiscountPercent) throws MerchantNotFound;

    String getMerchant(String merchantName) throws MerchantNotFound, InternalException;

    Double getDiscountRate(String merchantName) throws MerchantNotFound;

    void updateTotalDiscount(String merchantName, Double transactionAmount);
}
