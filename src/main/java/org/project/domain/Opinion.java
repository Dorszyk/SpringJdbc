package org.project.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Opinion {

    Long id;
    Customer customer;
    Product product;
    Byte stars;
    String comment;
    OffsetDateTime dateTime;
}