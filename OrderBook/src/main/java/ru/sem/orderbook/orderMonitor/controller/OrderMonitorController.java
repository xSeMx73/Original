package ru.sem.orderbook.orderMonitor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sem.orderbook.orderMonitor.dto.PendOrderDto;
import ru.sem.orderbook.orderMonitor.service.PendOrderService;

import java.util.List;

@Slf4j
@RequestMapping(path = "/monitor")
@RequiredArgsConstructor
@RestController
public class OrderMonitorController {

    private final PendOrderService pendOrderService;

    @GetMapping()
    public ResponseEntity<List<PendOrderDto>> getPendOrders() {
        log.info("<----ORDER BOOK Controller Get getPendOrders");
        return ResponseEntity.ok().body(pendOrderService.getPendOrders());
    }

    @PostMapping
    public void setReason(@RequestHeader String reason, @RequestHeader String id) {
        log.info("<----ORDER BOOK Controller Set reason");
        pendOrderService.setReason(reason, id);
    }

}
