package ru.sem.clientbase.client.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.sem.clientbase.client.dto.ClientResponseDto;
import ru.sem.clientbase.client.repository.ClientRepository;


@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    @Qualifier("mvcConversionService")
    private final ConversionService converter;

    public ClientResponseDto getClient(String query) {
        if (query != null) {
           return converter.convert(clientRepository.findByQuery(query), ClientResponseDto.class);
        } else {
            return null;
        }

    }

}
