package com.ankur.prog.productservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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

