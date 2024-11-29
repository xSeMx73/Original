package ru.sem.clientbase.transport.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TransportResponseDto {

    Long id;

    String brandName;

    String model;

    String addInform;

    String vin;

    String gosNumber;

    Integer year;
}
