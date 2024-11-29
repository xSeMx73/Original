package ru.sem.clientbase.client.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sem.clientbase.client.dto.ClientDto;
import ru.sem.clientbase.client.dto.ClientResponseDto;
import ru.sem.clientbase.client.service.ClientService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/clients")
public class ClientController {

   private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {

        return null;
    }

    @GetMapping
    public ClientResponseDto getClient(@RequestParam String query){
        log.info("Запрос клиента с параметром {}", query);
        return clientService.getClient(query);

    }

}
