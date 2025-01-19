package ru.sem.gateway.orderBook;

import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    Long id;

    String info;

    String dealer;

    String productName;

    String brand;

    String article;

    Integer quantity;

    String price;

    String manager;

}