package com.project.bankapi.model.mProduct;

import com.project.bankapi.enums.Status;
import com.project.bankapi.model.mUser.AccountHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "productId")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account extends Product {

    private String secretKey;
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int minimumBalance = 0;
    private int monthlyMaintenanceFee = 0;
    private static String defaultSecretKey = "4321";
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Account() {}

    //constructor para checkingAc
    public Account(BigDecimal balance, AccountHolder firstowner, int minimumBalance, int monthlyMaintenanceFee) {
        super(balance, firstowner);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.secretKey = passwordEncoder.encode(defaultSecretKey);
        this.creationDate = new Date();
        this.status = Status.ACTIVE;
    }
    //constructor para checkingAc   con secondOwner
    public Account(BigDecimal balance, AccountHolder firstowner, AccountHolder secondowner, int minimumBalance, int monthlyMaintenanceFee) {
        super(balance, firstowner, secondowner);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.secretKey = passwordEncoder.encode(defaultSecretKey);
        this.creationDate = new Date();
        this.status = Status.ACTIVE;
    }

    //constructor para checkingSTUDENTAc
    public Account(BigDecimal balance, AccountHolder firstowner) {
        super(balance, firstowner);
        this.secretKey = passwordEncoder.encode(defaultSecretKey);
        this.creationDate = new Date();
        this.status = Status.ACTIVE;
    }
    //constructor para checkingSTUDENTAc  con secondOwner
    public Account(BigDecimal balance, AccountHolder firstowner, AccountHolder secondowner) {
        super(balance, firstowner, secondowner);
        this.secretKey = passwordEncoder.encode(defaultSecretKey);
        this.creationDate = new Date();
        this.status = Status.ACTIVE;
    }

    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) {
        this.secretKey = passwordEncoder.encode(secretKey);
    }

    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public int getMinimumBalance() { return minimumBalance; }
    public void setMinimumBalance(int minimumBalance) { this.minimumBalance = minimumBalance; }

    public int getMonthlyMaintenanceFee() { return monthlyMaintenanceFee; }
    public void setMonthlyMaintenanceFee(int monthlyMaintenanceFee) {this.monthlyMaintenanceFee = monthlyMaintenanceFee;}

    public static String getDefaultSecretKey() {  return defaultSecretKey; }
    public static void setDefaultSecretKey(String defaultSecretKey) { Account.defaultSecretKey = defaultSecretKey; }
}
