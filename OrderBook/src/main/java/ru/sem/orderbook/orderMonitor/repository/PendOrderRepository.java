package ru.sem.orderbook.orderMonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sem.orderbook.orderMonitor.model.PendOrder;

import java.util.List;

@Repository
public interface PendOrderRepository extends JpaRepository<PendOrder, Long> {

    @Query("select p from PendOrder as p where p.reason is null ")
    List<PendOrder> findAllWhereReasonIsNull();


}
