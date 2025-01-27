package ru.sem.orderbook.orderMonitor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.repository.OrderBookRepository;
import ru.sem.orderbook.orderMonitor.dto.PendOrderDto;
import ru.sem.orderbook.orderMonitor.model.PendOrder;
import ru.sem.orderbook.orderMonitor.model.ReturnReason;
import ru.sem.orderbook.orderMonitor.repository.PendOrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PendOrderService {

    @Qualifier("mvcConversionService")
    private final ConversionService converter;

    private final OrderBookRepository orderBookRepository;
    private final PendOrderRepository pendOrderRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    private void updatePendOrderTable() {
    List<PendOrder> pendOrders = orderBookRepository.findClientsOrders()
            .stream()
            .filter(s -> Objects.equals(s.getDeliveryTime(), LocalDate.now()))
            .map(s -> converter.convert(s, PendOrder.class))
            .toList();
        pendOrderRepository.saveAll(pendOrders);
    }

    public List<PendOrderDto> getPendOrders() {
        return pendOrderRepository.findAllWhereReasonIsNull().stream()
                .map(s -> converter.convert(s, PendOrderDto.class))
                .toList();

    }

    public void setReason(String reason, String id) {
        PendOrder pendOrder = getPendOrder(id);
        pendOrder.setReason(ReturnReason.valueOf(reason));
        pendOrderRepository.save(pendOrder);
    }

    private PendOrder getPendOrder(String id) {
        return pendOrderRepository.findById(Long.valueOf(id))
                .orElseThrow(NoSuchElementException::new);
    }
}
