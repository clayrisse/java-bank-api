package com.project.bankapi.controller.impl;

import com.project.bankapi.controller.i.IAdminController;
import com.project.bankapi.dto.CheckingAcDTO;
import com.project.bankapi.dto.CreditCardDTO;
import com.project.bankapi.dto.HolderDTO;
import com.project.bankapi.dto.SavingsDTO;
import com.project.bankapi.model.mProduct.*;
import com.project.bankapi.model.mUser.AccountHolder;
import com.project.bankapi.model.mUser.Address;
import com.project.bankapi.model.mUser.User;
import com.project.bankapi.repository.*;
import com.project.bankapi.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("/admin/")
public class AdminController implements IAdminController {

    @Autowired    AccountHolderService accountHolderService;
    @Autowired    AdminService adminService;
    @Autowired    CreditCardService creditCardService;
    @Autowired    ThirdPartyService thirdPartyService;
    @Autowired    AccountService accountService;
    @Autowired    ProductService productService;
    @Autowired    UserService userService;
//-------------------------------------------------

    @Autowired    AccountHolderRepository accountHolderRepository;
    @Autowired    AdminRepository adminRepository;
    @Autowired    CheckingAcRepository checkingAcRepository;
    @Autowired    CheckStudentAcRepository checkStudentAcRepository;
    @Autowired    CreditCardRepository creditCardRepository;
    @Autowired    SavingsAcRepository savingsAcRepository;
    @Autowired    ThirdPartyRepository thirdPartyRepository;
    @Autowired    AccountRepository accountRepository;
    @Autowired    ProductRepository productRepository;
    @Autowired    UserRepository userRepository;

//    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping("create/holder")                    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createNewHolder(@RequestBody HolderDTO holderDTO, @AuthenticationPrincipal UserDetails userDetails) {
        return adminService.createNewHolder(holderDTO, userDetails);
    }

    @PostMapping("product/create/checking-account")  @ResponseStatus(HttpStatus.CREATED)
    public Product createCheckingAccount(@RequestBody CheckingAcDTO checkingDTO, @AuthenticationPrincipal UserDetails userDetails) {
        return adminService.createCheckingAccount(checkingDTO, userDetails);
    }

    @PostMapping("product/create/savings-account")   @ResponseStatus(HttpStatus.CREATED)
    public Product createSavingsAccount(@RequestBody SavingsDTO savingsDTO, @AuthenticationPrincipal UserDetails userDetails) {
        return adminService.createSavingsAccount(savingsDTO, userDetails);
    }

    @PostMapping("product/create/credit-card")       @ResponseStatus(HttpStatus.CREATED)
    public Product createCreditCard(@RequestBody CreditCardDTO creditCardDTO, @AuthenticationPrincipal UserDetails userDetails) {
        return adminService.createCreditCard(creditCardDTO, userDetails);
    }

}