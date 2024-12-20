package ru.sem.gateway.transport;

import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TransportDto {

    Long id;

    String brandName;

    String model;

    String addInform;

    String vin;

    String gosNumber;

    Integer year;
}
