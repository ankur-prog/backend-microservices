package com.ankur.prog.productservice.repository;


import com.ankur.prog.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<List<Product>> findByBrandIgnoreCase(String brandName);

    List<Product> findByProductCategoryAndBrandIgnoreCase(String productCategory, String brandName);


}
