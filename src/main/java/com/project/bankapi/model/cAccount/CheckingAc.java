package com.project.bankapi.model.cAccount;

import javax.persistence.*;

@Entity
//@PrimaryKeyJoinColumn(name = "checkId")
public class CheckingAc extends Account{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long checkId;
    private String privateKey;


}


