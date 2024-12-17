package ru.sem.clientbase.client.model.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.clientbase.client.dto.ClientDto;
import ru.sem.clientbase.client.model.Client;

@RequiredArgsConstructor
@Component
public class ClientToClientDto implements Converter<Client, ClientDto> {


    @Override
    public ClientDto convert(Client source) {
        return ClientDto.builder()
                .id(source.getId())
                .name(source.getName())
                .lastName(source.getLastName())
                .nickName(source.getNickName())
                .email(source.getEmail())
                .phone(source.getPhone())
                .build();
    }
}