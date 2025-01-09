package ru.sem.orderbook.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sem.orderbook.order.model.Order;

@Repository
public interface OrderBookRepository extends JpaRepository<Order, Long> {
}
