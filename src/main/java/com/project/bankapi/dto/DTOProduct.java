package com.project.bankapi.dto;

import com.project.bankapi.model.mProduct.Money;
import com.project.bankapi.model.mUser.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

public class DTOProduct {


    private BigDecimal balanceAmount;
//    private Currency balanceCurrency;

    private String firstownerId;
//    private AccountHolder secondowner;

//      en el caso many to many
//    private Set<AccountHolder> owners;

}
