package com.project.bankapi.dto;

import java.math.BigDecimal;

public class SavingsDTO {

    private BigDecimal balanceAmount;
    //    private Currency balanceCurrency;
    private long firstownerId;
    private long secondownerId ;
    private int savingsDTOMinBalance;
    private double interestDTORate;



    public BigDecimal getBalanceAmount() { return balanceAmount; }
    public void setBalanceAmount(BigDecimal balanceAmount) { this.balanceAmount = balanceAmount; }

    public long getFirstownerId() { return firstownerId; }
    public void setFirstownerId(long firstownerId) { this.firstownerId = firstownerId;}

    public long getSecondownerId() { return secondownerId; }
    public void setSecondownerId(long secondownerId) { this.secondownerId = secondownerId; }

    public int getSavingsDTOMinBalance() { return savingsDTOMinBalance; }
    public void setSavingsDTOMinBalance(int savingsDTOMinBalance) {
        this.savingsDTOMinBalance = savingsDTOMinBalance;
    }

    public double getInterestDTORate() { return interestDTORate; }
    public void setInterestDTORate(double interestDTORate) { this.interestDTORate = interestDTORate; }
}
