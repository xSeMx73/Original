package ru.sem.clientbase.client.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sem.clientbase.client.dto.ClientDto;
import ru.sem.clientbase.client.dto.ClientResponseDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/clients")
public class ClientController {

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {

        return null;
    }

    @GetMapping
    public ResponseEntity<ClientResponseDto> getClient(@RequestParam String query){
        log.info("Запрос клиента с параметром {}", query);

    }

}
