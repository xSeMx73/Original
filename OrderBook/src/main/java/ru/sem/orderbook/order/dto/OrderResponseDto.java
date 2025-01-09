package ru.sem.orderbook.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    Long id;

    String info;

    String dealer;

    String productName;

    String brand;

    String article;

    Integer quantity;

    Double price;

    String manager;

    LocalDate deliveryTime;

    LocalDateTime createTime;
}
