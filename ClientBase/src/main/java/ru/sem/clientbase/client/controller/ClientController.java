package ru.sem.clientbase.client.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sem.clientbase.client.dto.ClientDto;
import ru.sem.clientbase.client.dto.ClientResponseDto;
import ru.sem.clientbase.client.service.ClientService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/clients")
@CrossOrigin(origins = "http://192.168.1.201:8080")
public class ClientController {

   private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {

        return null;
    }

    @GetMapping
    public List<ClientResponseDto> getClient(@RequestParam String query){
        log.info("Запрос клиента с параметром {}", query);
        List<ClientResponseDto> clients = new ArrayList<>();
               clients.add(clientService.getClient(query));
        log.info("Возвращаю данные запроса {}", clients);
        return clients;

    }

}
