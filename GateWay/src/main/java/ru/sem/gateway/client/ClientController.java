package ru.sem.gateway.client;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/clients")
public class ClientController {

    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto ) {

    return null;
    }



}
