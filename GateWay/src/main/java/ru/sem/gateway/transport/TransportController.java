package ru.sem.gateway.transport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transport")
@CrossOrigin(origins = "http://192.168.1.201:9090")
public class TransportController {

    private final TransportWebClient webClient;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<TransportDto> createTransport(@RequestBody TransportDto transportData, @RequestHeader(name = "id") Long id) {
        log.info("<---GATEWAY TRANSPORT CONTROLLER Попытка добавления транспорта {} клиенту с ID : {}", transportData, id );
        TransportDto transportDto = webClient.addTransport(transportData, id);
        log.info("Успешное добавление транспорта в Gateway {} клиенту с ID : {}", transportData, id );
        return ResponseEntity.status(HttpStatus.CREATED).body(transportDto);
    }


    @DeleteMapping("/{transportToDeleteId}")
    public void deleteTransport (@PathVariable (name = "transportToDeleteId") Long id) {
        log.info("<---GATEWAY TRANSPORT CONTROLLER Попытка удаления транспорта с ID: {}", id);
        webClient.deleteTransport(id);
    }

    @PatchMapping
    public TransportDto updateTransport (@RequestBody TransportDto transportDto) {
        log.info("<---GATEWAY TRANSPORT CONTROLLER Попытка обновления транспорта {}", transportDto);
        return webClient.updateTransport(transportDto);
    }
}