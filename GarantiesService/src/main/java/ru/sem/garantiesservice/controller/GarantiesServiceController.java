package ru.sem.garantiesservice.controller;




import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sem.garantiesservice.dto.GarantRequestDto;
import ru.sem.garantiesservice.service.GarantService;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/garanties")
public class GarantiesServiceController {

    private final GarantService garantService;

    @PostMapping
    public GarantRequestDto createRequest(@RequestBody GarantRequestDto request) {
        log.info("<--- GARANTIESSERVICE Попытка создания рекламации {}", request);
        GarantRequestDto requestDto = garantService.createGarantRequest(request);
        log.info("<--- GARANTIESSERVICE Рекламация создана {}", requestDto);
        return request;
    }

    @GetMapping
    public List<GarantRequestDto> getRequests() {
        log.info("<--- GARANTIESSERVICE Попытка запроса рекламаций");
        List<GarantRequestDto> garantRequestDtoList = garantService.getGarantRequestDtoList();
        log.info("<--- GARANTIESSERVICE Список рекламация успешно отправлен {}", garantRequestDtoList);
        return garantRequestDtoList;
    }

    @GetMapping("/{id}")
    public GarantRequestDto getRequestById(@PathVariable long id) {
        log.info("<--- GARANTIESSERVICE GARANTIES CONTROLLER Запрос рекламации с id {}", id);
        GarantRequestDto requestDto = garantService.getGarantiesDtoById(id);
        log.info("<--- GARANTIESSERVICE GARANTIES CONTROLLER Успешный вывод рекламации {}",requestDto);
        return requestDto;
    }

    @PatchMapping
    public GarantRequestDto updateRequest(@RequestBody GarantRequestDto request) {
        log.info("<--- GARANTIESSERVICE Попытка обновления рекламации {}", request);
        GarantRequestDto requestDto = garantService.updateGarantRequest(request);
        log.info("<--- GARANTIESSERVICE Рекламация обновлена {}", requestDto);
        return request;
    }

    @GetMapping("/zakaz/{id}/{normHours}/{price}")
    public byte[] generateZakaz(@PathVariable Long id,
                                       @PathVariable Long normHours,
                                       @PathVariable Long price,
                                       @RequestHeader String job) throws IOException {
        log.info("<--- GARANTIESSERVICE CONTROLLER Запрос заказ-наряда по рекламации с id {}", id);
        byte[] zakaz = garantService.createZakaz(id, normHours, price, job);
        log.info("<--- GARANTIESSERVICE CONTROLLER Заказ-наряд по рекламации с id {} сформирован", id);
        return zakaz;
    }

    @GetMapping("/defekt/{id}")
    public byte[] generateDefekt(@PathVariable Long id) {
        log.info("<--- GARANTIESSERVICE CONTROLLER Запрос акта-дефектовки по рекламации с id {}", id);
        byte[] defekt = garantService.createDefekt(id);
        log.info("<--- GARANTIESSERVICE CONTROLLER Акт-дефектовки по рекламации с id {} сформирован", id);
        return defekt;
    }
}
