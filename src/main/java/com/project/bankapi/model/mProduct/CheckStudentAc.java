package com.project.bankapi.model.mProduct;

import com.project.bankapi.model.mUser.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@PrimaryKeyJoinColumn(name = "productId")
public class CheckStudentAc extends Account {


    public CheckStudentAc() {}
//
//    public CheckStudentAc(BigDecimal balance, AccountHolder firstowner) {
//        super(checkBalanceOpening(balance), firstowner);
//    }
//
//    public CheckStudentAc(BigDecimal balance, AccountHolder firstowner, AccountHolder secondowner) {
//        super(checkBalanceOpening(balance), firstowner, secondowner);
//    }

//    public static BigDecimal checkBalanceOpening(BigDecimal balance) {
//        if (balance.intValue() < 0) {
//            throw new IllegalArgumentException("El balance es menor a 0");
//        }
//        return balance;
//    }
}
