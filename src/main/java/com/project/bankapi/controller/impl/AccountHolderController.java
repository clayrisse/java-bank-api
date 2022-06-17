package com.project.bankapi.controller.impl;

import com.project.bankapi.controller.i.IAccountHolderController;
import com.project.bankapi.dto.HolderDTO;
import com.project.bankapi.model.mProduct.Product;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/holder/")
public class AccountHolderController implements IAccountHolderController {


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

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("product/all")

    public List<Product> getAllProducts(@AuthenticationPrincipal UserDetails userDetails) {
//    public void getAllProducts(@AuthenticationPrincipal UserDetails userDetails) {
        User holder = new User (userDetails.getUsername(), userDetails.getPassword());
            System.err.println("-----------1---");
        if (accountHolderRepository.findByUsername(holder.getUsername()).isPresent()) {
            System.err.println("------------2--");
//            if(accountHolderRepository.findById(holder.getId()).isPresent()) {
//
            AccountHolder accountHolder = accountHolderRepository.findByUsername(holder.getUsername()).get();
//            AccountHolder accountHolder = accountHolderRepository.findById(holder.getId()).get();
//            }
            List<Product> megalist = new ArrayList<>();
            megalist.addAll(accountHolder.getFirstownerList());
            megalist.addAll(accountHolder.getSecondownerList());

            return megalist;
        }
        throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Account holder not found");
    }


}
