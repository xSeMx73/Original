package ru.sem.gateway.orderBook.orderMonitor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:9090"})
@RequestMapping(path = "/monitor")
@RestController
public class OrderMonitorController {

    private final OrderMonitorWebClient orderMonitorWebClient;

    @GetMapping()
    public Flux<PendOrderDto> getManagers(){
        log.info("<--- Запрос списка на возврат");
      return orderMonitorWebClient.getPendOrders();
    }

    @PostMapping
    public void setReason(@RequestHeader String reason, @RequestHeader String id){
        log.info("<--- Удаление из мониторинга заказа с ID: {} по причине: {}", id, reason);
        orderMonitorWebClient.setReason(reason, id);
    }
}
