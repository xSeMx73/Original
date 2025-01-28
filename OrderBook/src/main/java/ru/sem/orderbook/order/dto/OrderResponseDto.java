package ru.sem.orderbook.order.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime createTime;
}
