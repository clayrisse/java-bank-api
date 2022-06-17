package com.project.bankapi.model.mOperation;

import com.project.bankapi.enums.OpType;
import com.project.bankapi.model.mProduct.Product;
import com.project.bankapi.model.mUser.AccountHolder;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private OpType opType;
    private BigInteger amount;
    private LocalDateTime dateTime;
    private long toHolderId;
    private String toUsername;
    private long fromHolderId;
    private String fromUsername;
    @ManyToOne
    @JoinColumn(name = "operationId")
    private Product productOwner;

    public Operation() {
        this.dateTime = LocalDateTime.now();
    }

    public Operation(OpType opType) {
        this.opType = opType;
        this.dateTime = LocalDateTime.now();
    }

    public Operation(OpType opType, BigInteger amount, long toHolderId, String toUsername, long fromHolderId, String fromUsername, Product productOwner) {
        this.opType = opType;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
        this.toHolderId = toHolderId;
        this.toUsername = toUsername;
        this.fromHolderId = fromHolderId;
        this.fromUsername = fromUsername;
        this.productOwner = productOwner;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public OpType getOpType() { return opType; }
    public void setOpType(OpType opType) { this.opType = opType; }

    public BigInteger getAmount() { return amount; }
    public void setAmount(BigInteger amount) { this.amount = amount; }

    public LocalDateTime getDateTime() { return dateTime;  }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public Product getProductOwner() { return productOwner; }
    public void setProductOwner(Product productOwner) { this.productOwner = productOwner; }

    public long getToHolderId() { return toHolderId; }
    public void setToHolderId(long toHolderId) { this.toHolderId = toHolderId; }

    public String getToUsername() { return toUsername; }
    public void setToUsername(String toUsername) { this.toUsername = toUsername; }

    public long getFromHolderId() { return fromHolderId; }
    public void setFromHolderId(long fromHolderId) { this.fromHolderId = fromHolderId; }

    public String getFromUsername() { return fromUsername; }
    public void setFromUsername(String fromUsername) { this.fromUsername = fromUsername; }
}
