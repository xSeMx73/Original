package ru.sem.clientbase.client.controller;

import static ru.sem.Config.BASE_URL;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import ru.sem.clientbase.client.dto.ClientDto;
import ru.sem.clientbase.client.dto.ClientResponseDto;
import ru.sem.clientbase.client.service.ClientService;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/clients")
@CrossOrigin(origins = BASE_URL + ":8080")
public class ClientController {


   private final ClientService clientService;

    @GetMapping
    public List<ClientResponseDto> getClient(@RequestParam String query){
        query = UriUtils.decode(query, StandardCharsets.UTF_8);
        log.info("Запрос клиента с параметром {}", query);
        List<ClientResponseDto> clients = new ArrayList<>(clientService.getClient(query));
        log.info("Возвращаю данные запроса {}", query);
        return clients;
    }

    @PostMapping
    public ClientDto createClient(@RequestBody @Valid ClientDto clientDto){
        log.info("Попытка создания нового клиента {}", clientDto);
        ClientDto client = clientService.createClient(clientDto);
        log.info("Клиент создан {}", client);
        return client;
    }

}
