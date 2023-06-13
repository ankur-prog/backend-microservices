package com.ankur.prog.productservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ProductRequest {
    private String productName;
    private String productCategory;
    private String brand;
    private String description;
    private Double price;
    private String currency;
    private LocalDate releaseDate;
    private String origin;
    private String battery;
    private Boolean touchId;
    private Boolean exchangePossible;
    private Double displaySize;
    private Boolean faceID;
}

