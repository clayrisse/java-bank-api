package com.project.bankapi.controller.impl;

import com.project.bankapi.controller.i.IUserController;
import com.project.bankapi.model.mUser.AccountHolder;
import com.project.bankapi.model.mUser.User;
import com.project.bankapi.repository.AccountHolderRepository;
import com.project.bankapi.repository.UserRepository;
import com.project.bankapi.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
public class UserController implements IUserController {

    @Autowired    UserService userService;
    @Autowired    AccountHolderService accountHolderService;
    @Autowired    AdminService adminService;
    @Autowired    ThirdPartyService thirdPartyService;
    @Autowired    ProductService productService;
    @Autowired    CreditCardService creditCardService;
    @Autowired    AccountService accountService;



    @Autowired    UserRepository userRepository;
    @Autowired    AccountHolderRepository accountHolderRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @GetMapping("/say-hi")
    public String sayHi() {
        return "Hi";
    }

    @PostMapping("create-holder")
//    public AccountHolder createNewUser(@RequestBody User user, @AuthenticationPrincipal UserDetails userDetails){
    public AccountHolder createNewUser(@RequestBody AccountHolder accountHolder, @AuthenticationPrincipal UserDetails userDetails) {
//    public AccountHolder createNewUser(@RequestBody AccountHolder accountHolder) {
        User user = new User (userDetails.getUsername(), userDetails.getPassword());
        System.err.println(user.getUsername());
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            //todo orrrrrrrrrrrrr password doent match the password to  the admin
            System.err.println("entro al error");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Username already exists");
        }
        AccountHolder acc1 = accountHolderRepository.save(new AccountHolder(accountHolder.getUsername(),
                passwordEncoder.encode(accountHolder.getPassword())));
        acc1.setRole("HOLDER");
        return accountHolderRepository.save(acc1);

    }

//    @PostMapping("/create-user")
//    public User createNewUser(@RequestBody User user, @AuthenticationPrincipal UserDetails userDetails){
//        User userCreador = new User(userDetails.getUsername(), userDetails.getPassword());
//        System.out.println(userCreador.getUsername());
//        if (userRepository.findByUsername(user.getUsername()).isPresent()){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "Username already exists");
//        }
//
//        User user1 = userRepository.save(new User(user.getUsername(),
//                passwordEncoder.encode(user.getPassword())));
//        user1.setRole("USER");
//        return userRepository.save(user1);
//    }
//
//    @GetMapping("/modify-password")
//    public void modifyPassword(@RequestParam String newPassword, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userRepository.findByUsername(userDetails.getUsername()).get();
//        user.setPassword(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//
//    }
//
//    @GetMapping("/get-user-info")
//    public User showToUsersOnly(@AuthenticationPrincipal UserDetails userDetails) {
//        return userRepository.findByUsername(userDetails.getUsername()).get();
//    }








}
