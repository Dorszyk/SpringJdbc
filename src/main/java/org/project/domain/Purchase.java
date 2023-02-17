package org.project.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    Long id;
    Customer customer;
    Product product;
    Integer quantity;
    OffsetDateTime dateTime;
}