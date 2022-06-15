package com.project.bankapi.dto;

import java.math.BigDecimal;

public class CheckingAcDTO {

    private int age;
    private BigDecimal balanceAmount;
//    private Currency balanceCurrency;

    private long firstownerId;
    private long secondownerId ;

    private int checkDTOMinBalance;
    private int checkDTOMonthMaintenanceFee;


//    private int minimumBalance;
//    private int monthlyMaintenanceFee;
    public CheckingAcDTO() {}

//    public CheckingAcDTO(int age, BigDecimal balanceAmount, long firstownerId) {
    public CheckingAcDTO(int age, BigDecimal balanceAmount, long firstownerId, int checkDTOMinBalance, int checkDTOMonthMaintenanceFee) {
        this.age = age;
        this.balanceAmount = balanceAmount;
        this.firstownerId = firstownerId;

        this.checkDTOMinBalance = checkDTOMinBalance;
        this.checkDTOMonthMaintenanceFee = checkDTOMonthMaintenanceFee;
    }


    //    public CheckingAcDTO(int age, BigDecimal balanceAmount, long firstownerId, long secondownerId) {
    public CheckingAcDTO(int age, BigDecimal balanceAmount, long firstownerId, long secondownerId, int checkDTOMinBalance, int checkDTOMonthMaintenanceFee) {
        this.age = age;
        this.balanceAmount = balanceAmount;
        this.firstownerId = firstownerId;
        this.secondownerId = secondownerId;

        this.checkDTOMinBalance = checkDTOMinBalance;
        this.checkDTOMonthMaintenanceFee = checkDTOMonthMaintenanceFee;
    }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public BigDecimal getBalanceAmount() { return balanceAmount; }
    public void setBalanceAmount(BigDecimal balanceAmount) { this.balanceAmount = balanceAmount; }

    public long getFirstownerId() { return firstownerId; }
    public void setFirstownerId(long firstownerId) { this.firstownerId = firstownerId; }

    public long getSecondownerId() { return secondownerId; }
    public void setSecondownerId(long secondownerId) { this.secondownerId = secondownerId; }

    public int getCheckDTOMinBalance() { return checkDTOMinBalance; }
    public void setCheckDTOMinBalance(int checkDTOMinBalance) { this.checkDTOMinBalance = checkDTOMinBalance; }

    public int getCheckDTOMonthMaintenanceFee() { return checkDTOMonthMaintenanceFee; }
    public void setCheckDTOMonthMaintenanceFee(int checkDTOMonthMaintenanceFee) { this.checkDTOMonthMaintenanceFee = checkDTOMonthMaintenanceFee; }
}
