package com.ankur.prog.productservice.controller;


import com.ankur.prog.productservice.dto.ProductRequest;
import com.ankur.prog.productservice.dto.ProductResponse;
import com.ankur.prog.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ankur
 * Product Controller API for all prdouct services
 */

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product",description = "Product API")
public class ProductController {

    private final ProductService productService;


    /**
     *Create products
     * @param productRequests
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Products")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Created")})
    public void createProduct(@RequestBody List<ProductRequest> productRequests) {
        productService.createProduct(productRequests);
    }

    /**
     *
     * @return all products from the database.
     */
    @GetMapping
    @Operation(summary = "Get all Products")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "204",description = "No Content")})
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return all products between two release dates
     */
    @GetMapping("/between-dates")
    @Operation(summary = "Get all products between two release dates")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "204",description = "No Content")})
    public ResponseEntity<List<ProductResponse>> getAllProductsBetweenDates(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ProductResponse> products = productService.getAllProductsBetweenDates(startDate, endDate);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     *
     * @param brandName
     * @return all products by brand name
     */
    @GetMapping("/brand/{brandName}")
    @Operation(summary = "Get all products by brand name")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "204",description = "No Content")})
    public ResponseEntity<List<ProductResponse>> getProductsByBrand(@PathVariable("brandName") String brandName) {
        List<ProductResponse> products = productService.getProductByBrand(brandName);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    /**
     *
     * @param productCategory
     * @param brandName
     * @return  all products by brand name and brand
     */
    @GetMapping("/byCategoryAndBrand")
    @Operation(summary = "Get all products by category and brand")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "204",description = "No Content")})
    public ResponseEntity<List<ProductResponse>> getProductByProductCategoryAndBrandName(@RequestParam("productCategory") String productCategory, @RequestParam("brandName") String brandName) {
        List<ProductResponse> productResponseList = productService.getProductByProductCategoryAndBrandName(productCategory, brandName);
        if (productResponseList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productResponseList, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param price
     * @return
     */
    @PutMapping("/{id}/price")
    @Operation(summary = "update price of the product")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "Product not found")})
    public ResponseEntity<String> updatePrice(@PathVariable("id") Long id, @RequestBody Double price) {
        Boolean updatePrice = productService.updatePrice(id, price);
        if (updatePrice) {
            return ResponseEntity.ok("Product is updated successfully.");
        } else return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param id
     * @return delete the product if present else return product not found as response.
     */
    @DeleteMapping("/{id}")
    @Operation(description = "Delete Product ")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "OK"),@ApiResponse(responseCode = "404",description = "Product Not Found")})
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        Boolean deleteProduct = productService.deleteProduct(id);
        if (deleteProduct) {
            return ResponseEntity.ok("Product is deleted successfully.");
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product id not found.");
    }

}


