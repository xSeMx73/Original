package ru.sem.gateway.garanties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:9090"})
@RequestMapping(path = "/garanties")
public class GarantiesController {

    private final GarantiesWebClient garantiesWebClient;

    @PostMapping
    public ResponseEntity<GarantRequestDto> createRequest(@RequestBody GarantRequestDto request) {
        log.info("<--- GATEWAY GARANTIES CONTROLLER попытка создание рекламации {}", request);
        GarantRequestDto requestDto = garantiesWebClient.createGarantRequest(request);
        log.info("<--- GATEWAY GARANTIES CONTROLLER рекламации создана {}", requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestDto);
    }

    @GetMapping
    public Flux<GarantResponseDto> getRequestsList() {
        log.info("<--- GATEWAY GARANTIES CONTROLLER попытка запроса рекламаций");
        Flux<GarantResponseDto> flux = garantiesWebClient.getGarantResponseList();
        log.info("<--- GATEWAY GARANTIES CONTROLLER попытка запроса рекламаций {}", flux.buffer().collectList().block());
        return garantiesWebClient.getGarantResponseList();
    }

}
