package com.project.bankapi.model.mProduct;

import com.project.bankapi.enums.Status;
import com.project.bankapi.model.mUser.AccountHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "productId")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account extends Product {

    private String secretKey;
    private LocalDate creationDay;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int minimumBalance = 0;
    private int monthlyMaintenanceFee = 0;
    private static String defaultSecretKey = "4321";
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Account() {
        this.secretKey = passwordEncoder.encode(defaultSecretKey);
        this.status = Status.ACTIVE;
        this.creationDay = LocalDate.now();
    }

    public Account(int minimumBalance, int monthlyMaintenanceFee) {
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.secretKey = passwordEncoder.encode(defaultSecretKey);
        this.status = Status.ACTIVE;
        this.creationDay = LocalDate.now();
    }

    public Account(int minimumBalance) {
        this.minimumBalance = minimumBalance;
        this.secretKey = passwordEncoder.encode(defaultSecretKey);
        this.status = Status.ACTIVE;
        this.creationDay = LocalDate.now();
    }

    @Override
//    public void setBalance(BigDecimal balance) {
    public void setBalance(Money balance) {
        if (balance.getAmount().intValue() < minimumBalance) {
            balance.decreaseAmount(new BigDecimal(Product.getPenaltyFee()));
            super.setBalance(balance);
            System.err.println("Se ha aplicado una penalización de 40 por balance menor al limite");
            throw new IllegalArgumentException("El balance total es menor al saldo mínimo de la cuenta");
        }
        super.setBalance(balance);
    }

//
//    public BigDecimal decreaseAmount(Money money) {
//        setAmount(this.amount.subtract(money.getAmount()));
//        return this.amount;
//    }
//
//    public BigDecimal decreaseAmount(BigDecimal addAmount) {
//        setAmount(this.amount.subtract(addAmount));
//        return this.amount;
//    }

    public void setFirstBalance(BigDecimal balance) {
        if (balance.intValue() < minimumBalance) {
            throw new IllegalArgumentException("El balance total es menor al saldo mínimo de la cuenta");
        }
        super.setBalance(new Money(balance));
    }


    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) {
        this.secretKey = passwordEncoder.encode(secretKey);
    }

    public LocalDate getCreationDay() { return creationDay; }
    public void setCreationDay(LocalDate creationDay) { this.creationDay = creationDay; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public int getMinimumBalance() { return minimumBalance; }
    public void setMinimumBalance(int minimumBalance) { this.minimumBalance = minimumBalance; }

    public int getMonthlyMaintenanceFee() { return monthlyMaintenanceFee; }
    public void setMonthlyMaintenanceFee(int monthlyMaintenanceFee) {this.monthlyMaintenanceFee = monthlyMaintenanceFee;}

    public static String getDefaultSecretKey() {  return defaultSecretKey; }
    public static void setDefaultSecretKey(String defaultSecretKey) { Account.defaultSecretKey = defaultSecretKey; }

}
