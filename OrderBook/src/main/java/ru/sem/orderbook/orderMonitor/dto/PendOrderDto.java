package ru.sem.orderbook.orderMonitor.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class PendOrderDto {

    Long id;

    String info;

    String productName;

    String brand;

    String article;

    Integer quantity;

    Double price;

    String manager;

    LocalDate inputData;

    LocalDate returnDate;

}
