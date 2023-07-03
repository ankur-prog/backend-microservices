package com.ankur.prog.productservice.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Ankur Kushwaha
 *Product contains all attributes of a product . Table name is t_product.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_product")
public class Product implements Serializable {
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

