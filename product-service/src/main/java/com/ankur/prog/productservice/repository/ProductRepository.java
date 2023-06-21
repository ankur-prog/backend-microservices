package com.ankur.prog.productservice.repository;


import com.ankur.prog.productservice.dto.ProductResponse;
import com.ankur.prog.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product,String> {

    List<ProductResponse> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);

    List<ProductResponse> findByBrandIgnoreCase(String brandName);

    List<ProductResponse> findByProductCategoryAndBrandIgnoreCase(String productCategory, String brandName);


}
