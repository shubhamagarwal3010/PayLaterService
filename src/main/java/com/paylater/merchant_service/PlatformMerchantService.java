package com.paylater.merchant_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paylater.merchant_service.exception.MerchantAlreadyExists;
import com.paylater.merchant_service.exception.MerchantDataNotValid;
import com.paylater.merchant_service.exception.MerchantNotFound;
import com.paylater.merchant_service.model.Merchant;
import com.paylater.user_service.exception.InternalException;
import com.paylater.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlatformMerchantService implements IPlatformMerchantService {

    private final Map<String, Merchant> merchantMap = new HashMap<>();

    @Override
    public void addMerchant(String merchantName, String email, Double discountPercent) throws MerchantAlreadyExists, MerchantDataNotValid {
        if (!merchantMap.containsKey(merchantName)) {
            Merchant merchant = new Merchant(merchantName, email, discountPercent);
            merchantMap.put(merchantName, merchant);
        } else {
            throw new MerchantAlreadyExists();
        }
    }


    @Override
    public void updateMerchant(String merchantName, Double newDiscountPercent) throws MerchantNotFound {
        if (merchantMap.containsKey(merchantName)) {
            Merchant merchant = merchantMap.get(merchantName);
            merchant.setDiscountPercent(newDiscountPercent);
            merchantMap.put(merchantName, merchant);
        } else {
            throw new MerchantNotFound();
        }
    }

    @Override
    public String getMerchant(String merchantName) throws MerchantNotFound, InternalException {
        if (merchantMap.containsKey(merchantName)) {
            try {
                return JsonUtil.objectToJson(merchantMap.get(merchantName));
            } catch (JsonProcessingException e) {
                throw new InternalException("Internal Server error during data parsing");
            }
        }
        throw new MerchantNotFound();
    }

    @Override
    public Double getDiscountRate(String merchantName) throws MerchantNotFound {
        if (merchantMap.containsKey(merchantName)) {
            return merchantMap.get(merchantName).getDiscountPercent();
        }
        throw new MerchantNotFound();
    }

    @Override
    public void updateTotalDiscount(String merchantName, Double transactionAmount) {
        Merchant merchant = merchantMap.get(merchantName);
        Double totalDiscount = merchant.getTotalDiscount();
        Double discountPercent = merchant.getDiscountPercent();
        merchant.setTotalDiscount(totalDiscount + ((100 - discountPercent) * transactionAmount) / 100);
        merchantMap.put(merchantName, merchant);
    }
}
