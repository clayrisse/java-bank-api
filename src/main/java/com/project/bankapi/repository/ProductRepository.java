package com.project.bankapi.repository;

import com.project.bankapi.model.mProduct.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
