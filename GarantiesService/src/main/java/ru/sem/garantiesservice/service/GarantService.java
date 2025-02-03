package ru.sem.garantiesservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sem.garantiesservice.dto.GarantRequestDto;
import ru.sem.garantiesservice.model.GarantRequest;
import ru.sem.garantiesservice.repository.GarantRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class GarantService {

    private final GarantRepository garantRepository;

    public GarantRequestDto createGarantRequest(GarantRequestDto request) {
        if (request != null){
  //          GarantRequest garantRequest = garantRepository.save(request);
        }
        return null;
    }
}
