package com.project.bankapi.repository;

import com.project.bankapi.model.cAccount.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
