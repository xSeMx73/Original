package ru.sem.gateway.client;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.sem.clientbase.client.dto.ClientResponseDto;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://192.168.1.201:9090")
@RequestMapping(path = "/clients")
public class ClientController {

  private final ClientWebClient webClient;

    public ClientDto createClient(@RequestBody ClientDto clientDto ) {

      return webClient.createClient(clientDto);

    }

  @GetMapping
  public Flux<ClientResponseDto> getClient(@RequestParam String query) {
    log.info("гейтвей, получение клиента в контроллере по запросу {} ", query);
      return webClient.getClient(query);
  }



}
