package com.project.bankapi.controller.impl;

import com.project.bankapi.service.IAccountService;
import com.project.bankapi.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController implements IAccountService {

    @Autowired    AccountHolderService accountHolderService;
    @Autowired    AdminService adminService;
    @Autowired    CheckingAcService checkingAcService;
    @Autowired    CheckStudentAcService checkStudentAcService;
    @Autowired    CreditCardService creditCardService;
    @Autowired    SavingsAcService service;
    @Autowired    ThirdPartyService thirdPartyService;
    @Autowired    AccountService accountService;
    @Autowired    ProductService productService;
}
