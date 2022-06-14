package com.project.bankapi;

import com.project.bankapi.model.mUser.AccountHolder;
import com.project.bankapi.model.mUser.Address;
import com.project.bankapi.model.mUser.User;
import com.project.bankapi.repository.AccountHolderRepository;
import com.project.bankapi.repository.RoleRepository;
import com.project.bankapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BankApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BankApiApplication.class, args);
	}

	@Autowired	UserRepository userRepository;
	@Autowired	RoleRepository roleRepository;
	@Autowired	AccountHolderRepository accountHolderRepository;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void run(String... args) throws Exception {
		User user = userRepository.save(new User("a", passwordEncoder.encode("a")));
		System.err.println(user.getUsername());
		user.setRole("ADMIN");

		userRepository.save(user);
		System.err.println(user.getPassword());

//		AccountHolder holder = accountHolderRepository.save(new AccountHolder("user1", "11111",
//				new Address ()));
		AccountHolder holder4 = accountHolderRepository.save(new AccountHolder("gbeegv","eererervg"));
	}

}
