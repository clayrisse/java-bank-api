package com.project.bankapi.model.mProduct;

import com.project.bankapi.enums.Status;
import com.project.bankapi.model.mUser.AccountHolder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
//@Data
//@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "productId")
public class CheckingAc extends Account{

    private static int checkingMinBalance = 250;
    private static int checkingMonthMaintenanceFee = 12;

    public CheckingAc() {
        super(checkingMinBalance, checkingMonthMaintenanceFee);
    }

    public CheckingAc(BigDecimal balance, AccountHolder firstowner) {
        super(checkMinBalanceOpening(balance), firstowner, checkingMinBalance, checkingMonthMaintenanceFee);
    }

    public CheckingAc(BigDecimal balance, AccountHolder firstowner, AccountHolder secondowner) {
        super(checkMinBalanceOpening(balance), firstowner, secondowner, checkingMinBalance, checkingMonthMaintenanceFee);
    }

    public CheckingAc(BigDecimal balance, AccountHolder firstowner, AccountHolder secondowner, int minimumBalance, int monthlyMaintenanceFee) {
        super(balance, firstowner, secondowner, minimumBalance, monthlyMaintenanceFee);
    }

    public static BigDecimal checkMinBalanceOpening(BigDecimal balance){
        if (balance.intValue() < checkingMinBalance) {
            throw new IllegalArgumentException("El balance es menor al minimo balance de checking acount");
        }
        return balance;
    }

    public static int getCheckingMinBalance() {
        return checkingMinBalance;
    }

    public static void setCheckingMinBalance(int checkingMinBalance) {
        CheckingAc.checkingMinBalance = checkingMinBalance;
    }

    @Override
    public void setMinimumBalance(int minimumBalance) {
        super.setMinimumBalance(minimumBalance);
    }

    @Override
    public void setMonthlyMaintenanceFee(int monthlyMaintenanceFee) {
        super.setMonthlyMaintenanceFee(monthlyMaintenanceFee);
    }

    public static int getCheckingMonthMaintenanceFee() {
        return checkingMonthMaintenanceFee;
    }

    public static void setCheckingMonthMaintenanceFee(int checkingMonthMaintenanceFee) {
        CheckingAc.checkingMonthMaintenanceFee = checkingMonthMaintenanceFee;
    }
}


