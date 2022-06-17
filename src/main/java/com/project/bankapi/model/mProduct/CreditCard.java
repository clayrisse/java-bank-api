package com.project.bankapi.model.mProduct;

import com.project.bankapi.model.mUser.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@PrimaryKeyJoinColumn(name = "productId")
public class CreditCard extends Product{

    private long id;
    private long creditLimit = 100;
    private double interestRate = 0.2;
    private LocalDate lastIntChargeDate =  LocalDate.of(2022,04,01);
    private long days = 7;


    public CreditCard() {
        super( new Money(new BigDecimal(0)));
        implementInterest();
    }

    public CreditCard(long creditLimit, double interestRate) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        implementInterest();
    }

//    @Override
//    public void setBalance(BigDecimal balance) {
//        super.setBalance(balance);
//
//    }

    public void implementInterest() {
        long days = ChronoUnit.DAYS.between(this.lastIntChargeDate, LocalDate.now());
//        System.err.println(getLastIntChargeDate() + "     " + days);
        long months = days/30;
        setLastIntChargeDate(this.lastIntChargeDate.plusMonths(months));
        Money fake = new Money(new BigDecimal(100));
        System.err.println(fake.getAmount() + "  " + months+ "  " + (fake.getAmount()).longValue()*0.1*months);
//        setBalance(getBalance().increaseAmount(new Money(new BigDecimal((getBalance().getAmount()).longValue()*0.1*months))));
//        System.err.println(getLastIntChargeDate());
    }

    public LocalDate getLastIntChargeDate() { return lastIntChargeDate; }
    public void setLastIntChargeDate(LocalDate lastIntChargeDate) { this.lastIntChargeDate = lastIntChargeDate; }

    public long getCreditLimit() { return creditLimit; }
    public void setCreditLimit(long creditLimit) {
        if (creditLimit < 100) throw new IllegalArgumentException("El límite de crédito no puede ser menor a 100");
        if (creditLimit > 100000) throw new IllegalArgumentException("El límite de crédito no puede ser mayor a 100000");
        this.creditLimit = creditLimit;
    }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) {
        if (interestRate < 0.1) throw new IllegalArgumentException("La tasa de interés no puede ser menor a 0.1");
        if (interestRate > 0.2) throw new IllegalArgumentException("La tasa de interés no puede ser mayor a 0.2");
        this.interestRate = interestRate;
    }
}

