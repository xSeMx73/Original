package ru.sem.orderbook.orderMonitor.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sem.orderbook.order.repository.OrderBookRepository;
import ru.sem.orderbook.order.service.OrderBookService;
import ru.sem.orderbook.orderMonitor.dto.PendOrderDto;
import ru.sem.orderbook.orderMonitor.model.PendOrder;
import ru.sem.orderbook.orderMonitor.model.ReturnReason;
import ru.sem.orderbook.orderMonitor.repository.PendOrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;


@Slf4j
@RequiredArgsConstructor
@Service
public class PendOrderService {

    @Qualifier("mvcConversionService")
    private final ConversionService converter;

    private final OrderBookRepository orderBookRepository;
    private final PendOrderRepository pendOrderRepository;
    private final OrderBookService orderBookService;

    @Scheduled(cron = "0 0 0 * * ?")
    @PostConstruct
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
  //  @PostConstruct
    @Scheduled(cron = "0 0 1 * * ?")
    private void autoSetReasonPendOrder() {
        long startTime = System.currentTimeMillis();
        int countCheckOrders = 0;
        List<PendOrder> pendOrdersForUpdate = pendOrderRepository.findAllWhereReasonIsNull();

        for(PendOrder order : pendOrdersForUpdate) {
            Integer oldCount = orderBookService
                    .getPartCount(order.getArticle(),
                            order.getBrand(),
                            orderBookService.dateBuilder(order.getInputData().withSecond(20)));
            Integer newCount = orderBookService.getPartCount(order.getArticle(), order.getBrand(),
                    orderBookService.dateBuilder(LocalDateTime.now()));
            countCheckOrders++;
            if (oldCount != null && newCount != null) {
                if (newCount < oldCount) {
                    order.setReason(ReturnReason.AUTODELETED);
                    pendOrderRepository.save(order);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("<---!!! Время выполнения обновления REASON {} ms", duration);
        log.info("<---!!! Обработано записей {}", countCheckOrders);
    }
}
