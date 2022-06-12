package com.project.bankapi.service.impl;

import com.project.bankapi.repository.*;
import com.project.bankapi.service.ICheckingAcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckingAcService implements ICheckingAcService {

    @Autowired    AccountHolderRepository accountHolderRepository;
    @Autowired    AdminRepository adminRepository;
    @Autowired    CheckingAcRepository checkingAcRepository;
    @Autowired    CheckStudentAcRepository checkStudentAcRepository;
    @Autowired    CreditCardRepository creditCardRepository;
    @Autowired    SavingsAcRepository savingsAcRepository;
    @Autowired    ThirdPartyRepository thirdPartyRepository;
    @Autowired    AccountRepository accountRepository;
    @Autowired    ProductRepository productRepository;

}
