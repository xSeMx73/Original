package ru.sem.clientbase.client.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.sem.clientbase.client.dto.ClientDto;
import ru.sem.clientbase.client.dto.ClientResponseDto;
import ru.sem.clientbase.client.model.Client;
import ru.sem.clientbase.client.repository.ClientRepository;
import ru.sem.clientbase.exception.NotFoundException;
import ru.sem.clientbase.exception.ParameterNotValidException;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    @Qualifier("mvcConversionService")
    private final ConversionService converter;

    public List<ClientResponseDto> getClient(String query) {
        if (!query.isBlank() && query.length() >= 3) {
           List<Client> clientsList = clientRepository.findAllByQuery(query);
           return clientsList.stream().map(cl -> converter.convert(cl, ClientResponseDto.class)).toList();
        } else {
            return null;
        }

    }

    public ClientDto createClient(ClientDto clientDto) {
        if (clientDto == null) {
            throw new ParameterNotValidException("Ошибка данных", "Пустое тело запроса POST");
        }
        Client client = converter.convert(clientDto, Client.class);
        if (client != null) {
            client = clientRepository.save(client);
        }
        return converter.convert(client, ClientDto.class);
    }

    public ClientResponseDto getClientForId(Long id) {
        return converter
                .convert(clientRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Клиент с таким ID: " + id + " не найден")),ClientResponseDto.class);
    }
}
