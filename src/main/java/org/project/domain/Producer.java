package org.project.domain;
import lombok.*;
@With
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producer {

    Long id;
    String producerName;
    String address;
}
