package ru.sem.garantiesservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sem.garantiesservice.dto.GarantRequestDto;
import ru.sem.garantiesservice.service.GarantService;

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
        return requestDto;
    }
}
