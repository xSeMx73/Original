package ru.sem.clientbase.transport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.sem.clientbase.transport.dto.TransportResponseDto;
import ru.sem.clientbase.transport.model.Transport;
import ru.sem.clientbase.transport.model.converter.TransportResponseDtoToTransport;
import ru.sem.clientbase.transport.repository.TransportRepository;

@RequiredArgsConstructor
@Service
public class TransportService {

    private final TransportRepository transportRepository;
    private final ConversionService converter;

    public TransportResponseDto addTransport(TransportResponseDto transportDto, Long clientId) {
        Transport transport = new Transport();
transport=
        transportRepository.save(transport);
        return null;
    }

}
