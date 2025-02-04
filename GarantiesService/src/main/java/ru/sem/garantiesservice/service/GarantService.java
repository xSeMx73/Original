package ru.sem.garantiesservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.sem.garantiesservice.dto.GarantRequestDto;
import ru.sem.garantiesservice.model.GarantRequest;
import ru.sem.garantiesservice.repository.GarantRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class GarantService {

    @Qualifier("mvcConversionService")
    private final ConversionService converter;
    private final GarantRepository garantRepository;

    public GarantRequestDto createGarantRequest(GarantRequestDto request) {
        if (request != null){
            request.setCreateDate(LocalDate.now());
            request.setStatus(GarantRequest.Status.CREATED);
            request.setLastUpdateStatusTime(LocalDate.now());
            GarantRequest garantRequest = garantRepository
                    .save(Objects.requireNonNull(converter.convert(request, GarantRequest.class)));
            return converter.convert(garantRequest, GarantRequestDto.class);
        }
        return null;
    }

    public List<GarantRequestDto> getGarantRequestDtoList() {
        return garantRepository.findAll().stream()
                .map(garant -> converter.convert(garant, GarantRequestDto.class))
                .toList();
    }
}
