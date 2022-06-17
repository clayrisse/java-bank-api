package com.project.bankapi.repository;

import com.project.bankapi.model.mProduct.Product;
import com.project.bankapi.model.mUser.AccountHolder;
import com.project.bankapi.model.mUser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
    Optional<AccountHolder> findById(long id);
//    List<Product>
    Optional<AccountHolder> findByUsername(String username);
}
