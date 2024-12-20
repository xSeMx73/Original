package ru.sem.gateway.client;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.sem.clientbase.client.dto.ClientResponseDto;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://192.168.1.201:9090")
@RequestMapping(path = "/clients")
public class ClientController {

  private final ClientWebClient webClient;

  @PostMapping
  public ClientDto createClient(@RequestBody ClientDto clientDto ) {
    log.info("<---GATEWAY CLIENT CONTROLLER Создание клиента {} ", clientDto);

      return webClient.createClient(clientDto);

    }

  @GetMapping
  public Flux<ClientResponseDto> getClient(@RequestParam String query) {
    log.info("<---GATEWAY CLIENT CONTROLLER Получение клиента по запросу {} ", query);
    if (query.isEmpty()){
      return null;
    }
      return webClient.getClient(query);
  }

  @GetMapping("/id/{id}")
  public ClientResponseDto getClientForId(@PathVariable Long id) {
    log.info("<---GATEWAY CLIENT CONTROLLER Получение клиента по ID {} ", id);
    if (id == null) {
      return null;
    }
    return webClient.getClientForId(id);
  }

  @DeleteMapping("{clientToDeleteId}")
  public void deleteClient(@PathVariable("clientToDeleteId") Long clientId) {
    log.info("<---GATEWAY CLIENT CONTROLLER Попытка удаления клиента с ID: {}", clientId);
    webClient.deleteClient(clientId);

  }

}
