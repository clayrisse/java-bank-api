package com.project.bankapi.service.impl;

import com.project.bankapi.controller.IProductService;
import com.project.bankapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService implements IProductService {

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
