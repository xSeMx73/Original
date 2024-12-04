package ru.sem.clientbase.transport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sem.clientbase.transport.dto.TransportResponseDto;
import ru.sem.clientbase.transport.repository.TransportRepository;

@RequiredArgsConstructor
@Service
public class TransportService {

    private final TransportRepository transportRepository;

    public TransportResponseDto addTransport(TransportResponseDto transport, Long clientId) {
        return null;
    }

}
