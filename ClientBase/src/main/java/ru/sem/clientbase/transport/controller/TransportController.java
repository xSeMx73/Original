package ru.sem.clientbase.transport.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.sem.clientbase.client.service.ClientService;
import ru.sem.clientbase.transport.dto.TransportResponseDto;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transport")
@CrossOrigin(origins = "http://192.168.1.201:8080")
public class TransportController {

    private final ClientService clientService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<TransportResponseDto> createTransport(@RequestBody TransportResponseDto transport, RequestParam clientId) {
        log.info("Добавление транспорта {} клиенту с ID : {}", transport, clientId );

        return null;
    }
}