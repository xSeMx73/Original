package ru.sem.gateway.garanties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
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

    @GetMapping("/{id}")
    public GarantRequestDto getRequestById(@PathVariable int id) {
        log.info("<--- GATEWAY GARANTIES CONTROLLER Запрос рекламации с id {}", id);
        GarantRequestDto requestDto = garantiesWebClient.getGarantiesById(id);
        log.info("<--- GATEWAY GARANTIES CONTROLLER Успешный вывод рекламации {}",requestDto);
        return requestDto;

    }

    @PatchMapping
    public ResponseEntity<GarantRequestDto> updateRequest(@RequestBody GarantRequestDto request) {
        log.info("<--- GATEWAY GARANTIES CONTROLLER попытка обновления рекламации {}", request);
        GarantRequestDto requestDto = garantiesWebClient.updateGarantRequest(request);
        log.info("<--- GATEWAY GARANTIES CONTROLLER рекламации обновлена {}", requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestDto);
    }

    @GetMapping("/zakaz/{id}/{normHours}/{price}")
    public ResponseEntity<byte[]> generateZakaz(@PathVariable Long id,
                                                       @PathVariable Long normHours,
                                                       @PathVariable Long price,
                                                       @RequestHeader String job) {
        log.info("<--- GATEWAY GARANTIES CONTROLLER Запрос заказ-наряда по рекламации с id {}", id);
        byte[] zakaz = garantiesWebClient.createZakaz(id, normHours, price, job);
        log.info("<--- GATEWAY GARANTIES CONTROLLER Заказ-наряд по рекламации с id {} сформирован", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
        headers.setContentDispositionFormData("attachment", "modified.xlsx");

       return new ResponseEntity<>(zakaz, headers, HttpStatus.OK);
    }

    @GetMapping("/defekt/{id}")
    public ResponseEntity<byte[]> generateDefekt(@PathVariable Long id) throws IOException {
        log.info("<--- GATEWAY GARANTIES CONTROLLER Запрос акта-дефектовки по рекламации с id {}", id);
        byte[] defekt = garantiesWebClient.createDefekt(id);
        log.info("<--- GATEWAY GARANTIES CONTROLLER Акт-дефектовки по рекламации с id {} сформирован", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
        headers.setContentDispositionFormData("attachment", "defekt.docx");
        return new ResponseEntity<>(defekt, headers, HttpStatus.OK);
    }

}
