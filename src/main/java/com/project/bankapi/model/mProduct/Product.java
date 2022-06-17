package com.project.bankapi.model.mProduct;

import com.project.bankapi.model.mOperation.Operation;
import com.project.bankapi.model.mUser.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "bal_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "bal_amount"))
    })
    private Money balance; //--- if balance < minbalance -->balance-penaltyFee
    public static int penaltyFee = 40;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "firstownerId")
    private AccountHolder firstowner;

    @ManyToOne
    @JoinColumn(name = "secondownerId") // cascade = CascadeType.ALL
    private AccountHolder secondowner;

    @OneToMany(mappedBy = "productOwner")
    private List<Operation> operationList;

    public Product() {}

    public Product(Money balance) {
        this.balance = balance;
    }

    public Product(BigDecimal balance, AccountHolder firstowner) {
        this.balance = new Money (balance);
        this.firstowner = firstowner;
    }

    public Product(BigDecimal balance, AccountHolder firstowner, AccountHolder secondowner) {
        this.balance = new Money (balance);
        this.firstowner = firstowner;
        this.secondowner = secondowner;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }
    public void setBalance(Money balance) {
        this.balance = balance;
    }
    public void setBalance(BigDecimal balance) {
            this.balance = new Money(balance);
    }

    public static int getPenaltyFee() {
        return penaltyFee;
    }
    public static void setPenaltyFee(int penaltyFee) {
        Product.penaltyFee = penaltyFee;
    }

    public AccountHolder getFirstowner() {
        return firstowner;
    }
    public void setFirstowner(AccountHolder firstowner) {
        this.firstowner = firstowner;
    }

    public AccountHolder getSecondowner() { return secondowner; }
    public void setSecondowner(AccountHolder secondowner) { this.secondowner = secondowner; }
}
