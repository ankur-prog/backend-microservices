package com.ankur.prog.productservice.controller;


import com.ankur.prog.productservice.dto.ProductRequest;
import com.ankur.prog.productservice.dto.ProductResponse;
import com.ankur.prog.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ankur
 * @version 1.0
 */

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    // 1 . Create product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody List<ProductRequest> productRequests) {
        productService.createProduct(productRequests);
    }

    // 2 . Get all product
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // 3. get products between two release date
    @GetMapping("/between-dates")
    public ResponseEntity<List<ProductResponse>> getAllProductsBetweenDates(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ProductResponse> products = productService.getAllProductsBetweenDates(startDate, endDate);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    // 4. get products by brand name
    @GetMapping("/brand/{brandName}")
    public ResponseEntity<List<ProductResponse>> getProductsByBrand(@PathVariable("brandName") String brandName){
        List<ProductResponse> products = productService.getProductByBrand(brandName);
        if(products.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);

    }
    // 5. get products by category and Brand
    @GetMapping("/byCategoryAndBrand")
    public  ResponseEntity<List<ProductResponse>> getProductByProductCategoryAndBrandName(@RequestParam("productCategory") String productCategory, @RequestParam("brandName") String brandName)
    {
        List<ProductResponse> productResponseList = productService.getProductByProductCategoryAndBrandName(productCategory,brandName);
        if(productResponseList.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productResponseList,HttpStatus.OK);
    }

    // 6. update product
    @PutMapping("/{id}/price")
    public ResponseEntity<String> updatePrice(
            @PathVariable("id") String id,@RequestBody Double price){
        Boolean updatePrice = productService.updatePrice(id,price);
        if(updatePrice)
        {
            return ResponseEntity.ok("Product updated successfully.");
        }
        else
            return ResponseEntity.notFound().build();
    }

    // 7 . delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id)
    {
        Boolean deleteProduct = productService.deleteProduct(id);
        if(deleteProduct)
        {
            return ResponseEntity.ok("Product deleted successfully.");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

}


