package ru.sem.clientbase.transport.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.sem.clientbase.client.model.Client;
import ru.sem.clientbase.client.repository.ClientRepository;
import ru.sem.clientbase.exception.NotFoundException;
import ru.sem.clientbase.transport.dto.TransportResponseDto;
import ru.sem.clientbase.transport.model.Transport;
import ru.sem.clientbase.transport.repository.TransportRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransportService {

    private final ClientRepository clientRepository;
    private final TransportRepository transportRepository;
    @Qualifier("mvcConversionService")
    private final ConversionService converter;

    public TransportResponseDto addTransport(TransportResponseDto transportDto, Long clientId) {
        Transport transport = new Transport();
        if(transportDto != null) {
            transport = converter.convert(transportDto, Transport.class);
        }
        Client client = clientRepository.findById(clientId).orElseThrow(() ->
                new NotFoundException("Клиент с ID: " + clientId + " не найден"));
        if (transport != null) {
            transport.setOwner(client);
            transport = transportRepository.save(transport);
            return converter.convert(transport, TransportResponseDto.class);
        }
        return transportDto;
    }

    public void deleteTransport(Long id) {
        getForId(id);
        transportRepository.deleteById(id);
    }

    public Transport getForId(Long id){
        return transportRepository.findById(id).orElseThrow(() -> new NotFoundException("Транспорт с ID: " + id + " не найден"));
    }

}
