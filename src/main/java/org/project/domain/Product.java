package org.project.domain;
import lombok.*;

import java.math.BigDecimal;

@With
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    Long id;
    String productCode;
    String productName;
    BigDecimal productPrice;
    Boolean adultsOnly;
    String description;
    Producer producer;
}