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

    public CheckingAc() {}

    public CheckingAc(BigDecimal balance, AccountHolder firstowner) {
        super(checkMinBalanceOpening(balance), firstowner, checkingMinBalance, checkingMonthMaintenanceFee);
    }

    public CheckingAc(BigDecimal balance, AccountHolder firstowner, AccountHolder secondowner) {
        super(checkMinBalanceOpening(balance), firstowner, secondowner, checkingMinBalance, checkingMonthMaintenanceFee);
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

    public static int getCheckingMonthMaintenanceFee() {
        return checkingMonthMaintenanceFee;
    }

    public static void setCheckingMonthMaintenanceFee(int checkingMonthMaintenanceFee) {
        CheckingAc.checkingMonthMaintenanceFee = checkingMonthMaintenanceFee;
    }
}


