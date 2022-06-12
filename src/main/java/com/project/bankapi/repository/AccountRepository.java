package com.project.bankapi.repository;

import com.project.bankapi.model.cAccount.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
