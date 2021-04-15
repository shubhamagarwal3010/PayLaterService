package com.paylater.merchant_service.model;

import com.paylater.merchant_service.exception.MerchantDataNotValid;
import com.paylater.utils.Validation;

import java.util.Date;
import java.util.Objects;

public class Merchant {
    private final Date createdAt;
    private String merchantName;
    private Double discountPercent;
    private String email;
    private Double totalDiscount = 0.0;
    private Date updatedAt;

    public Merchant(String merchantName, String email, Double discountPercent) throws MerchantDataNotValid {
        this.setMerchantName(merchantName);
        this.setEmail(email);
        this.discountPercent = discountPercent;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) throws MerchantDataNotValid {
        if (!Validation.isNameValid(merchantName)) {
            throw new MerchantDataNotValid("MerchantName is not valid");
        }
        this.merchantName = merchantName;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
        this.updatedAt = new Date();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws MerchantDataNotValid {
        if (!Validation.isNameValid(email)) {
            throw new MerchantDataNotValid("Merchant Email id is not valid");
        }
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchant that = (Merchant) o;
        return merchantName.equals(that.merchantName) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(merchantName, email);
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
