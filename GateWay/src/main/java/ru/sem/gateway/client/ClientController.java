package ru.sem.gateway.client;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import ru.sem.clientbase.client.dto.ClientResponseDto;
import static ru.sem.Config.BASE_URL;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = BASE_URL + ":9090")
@RequestMapping(path = "/clients")
public class ClientController {

  private final ClientWebClient webClient;

  @PostMapping
  public ClientDto createClient(@RequestBody ClientDto clientDto ) {
    log.info("гейтвей, создание клиента в контроллере {} ", clientDto);

      return webClient.createClient(clientDto);

    }

  @GetMapping
  public Flux<ClientResponseDto> getClient(@RequestParam String query) {
    log.info("гейтвей, получение клиента в контроллере по запросу {} ", query);
      return webClient.getClient(query);
  }



}
