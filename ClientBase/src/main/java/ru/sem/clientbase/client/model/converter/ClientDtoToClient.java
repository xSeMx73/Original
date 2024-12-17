package ru.sem.clientbase.client.model.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.clientbase.client.dto.ClientDto;
import ru.sem.clientbase.client.model.Client;

@RequiredArgsConstructor
@Component
public class ClientDtoToClient implements Converter<ClientDto, Client> {

    @Override
    public Client convert(ClientDto source) {
        Client client = new Client();
        client.setName(source.getName());
        client.setLastName(source.getLastName());
        client.setNickName(source.getNickName());
        client.setPhone(source.getPhone());
        client.setEmail(source.getEmail());
        return client;
    }
}
