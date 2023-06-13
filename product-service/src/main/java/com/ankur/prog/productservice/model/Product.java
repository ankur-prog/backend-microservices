package com.ankur.prog.productservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Generated;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Document(value = "Product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    private String id;
    private String productName;
    private String productCategory;
    private String brand;
    private String description;
    private Double price;
    private String currency;
    @JsonFormat(pattern = "dd-MM-YYYY")
    private LocalDate releaseDate;
    private String origin;
    private String battery;
    private Boolean touchId;
    private Boolean exchangePossible;
    private Double displaySize;
    private Boolean faceID;
    private LocalDateTime lastupdatedDate;

}

