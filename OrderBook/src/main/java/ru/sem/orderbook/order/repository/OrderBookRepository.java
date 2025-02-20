package ru.sem.orderbook.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sem.orderbook.order.model.Order;

import java.util.List;

@Repository
public interface OrderBookRepository extends JpaRepository<Order, Long> {


    @Query("select o " +
            "from Order AS o order by o.createTime desc limit 300")
    List<Order> findAllOrders();

    @Query("select o " +
            "from Order AS o where o.info not ilike 'склад' " +
            "and o.info not ilike 'скл' " +
            "and o.info not ilike 'crkfl' " +
            "and o.info not ilike 'crk' " +
            "and o.info is not null " +
            "and TRIM(o.info) <> '' order by o.createTime desc limit 300")
    List<Order> findClientsOrders();

    @Query("select o " +
            "from Order AS o where o.info not ilike 'склад' " +
            "and o.info not ilike 'скл' " +
            "and o.info not ilike 'crkfl' " +
            "and o.info not ilike 'crk' " +
            "and o.info is not null " +
            "and TRIM(o.info) <> '' " +
            "and o.dealer not ilike '%наш склад%' " +
            "and o.deliveryTime = current date " +
            "order by o.dealer")
    List<Order> findSortByDealerOrders();

    @Query("select o " +
            "from Order AS o where o.isDelivered = false " +
            "order by o.createTime desc limit 30")
    List<Order> findOrdersForUpdateDelivery();

}
