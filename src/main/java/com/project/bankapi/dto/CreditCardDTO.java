package com.project.bankapi.dto;

import java.math.BigDecimal;

public class CreditCardDTO {

//    private BigDecimal balanceAmount;
    //    private Currency balanceCurrency;
    private long firstownerId;
    private long secondownerId ;
    private long creditLimit;
    private double interestRate;


    public long getFirstownerId() { return firstownerId; }
    public void setFirstownerId(long firstownerId) { this.firstownerId = firstownerId; }

    public long getSecondownerId() { return secondownerId; }
    public void setSecondownerId(long secondownerId) { this.secondownerId = secondownerId; }

    public long getCreditLimit() { return creditLimit; }
    public void setCreditLimit(long creditLimit) { this.creditLimit = creditLimit; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
}
