package ru.sem.orderbook.orderMonitor.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Table(name = "pend_orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@Entity
public class PendOrder {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   String article;

    String info;

    String productName;

    String brand;

    Integer quantity;

    Double price;

    String manager;

    LocalDate inputData;

    LocalDate returnData;

    @Enumerated(EnumType.STRING)
    ReturnReason reason;
}
