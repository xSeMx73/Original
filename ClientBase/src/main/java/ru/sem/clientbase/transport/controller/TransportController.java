package ru.sem.clientbase.transport.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sem.clientbase.transport.dto.TransportResponseDto;
import ru.sem.clientbase.transport.service.TransportService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transport")
@CrossOrigin(origins = "http://192.168.1.201:8080")
public class TransportController {

    private final TransportService transportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<TransportResponseDto> createTransport(@RequestBody TransportResponseDto transportData, @RequestHeader(name = "id") Long id) {
        log.info("Попытка добавления транспорта {} клиенту с ID : {}", transportData, id );
        TransportResponseDto transportDto = transportService.addTransport(transportData, id);
        log.info("Успешное добавление транспорта {} клиенту с ID : {}", transportData, id );
        return ResponseEntity.status(HttpStatus.CREATED).body(transportDto);
    }


    @DeleteMapping()
    public void deleteTransport (@RequestHeader(name = "id") Long id) {
        log.info("Попытка удаления транспорта с ID: {}", id);
        transportService.deleteTransport(id);
    }
}