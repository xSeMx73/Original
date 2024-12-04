package ru.sem.clientbase.client.model;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.clientbase.client.dto.ClientResponseDto;
import ru.sem.clientbase.transport.model.converter.TransportToTransportResponseDto;

@RequiredArgsConstructor
@Component
public class ClientToClientResponseDto implements Converter<Client, ClientResponseDto> {


    private final TransportToTransportResponseDto converter;

    @Override
    public ClientResponseDto convert(Client source) {
        return ClientResponseDto.builder()
                .id(source.getId())
                .name(source.getName())
                .lastName(source.getLastName())
                .nickName(source.getNickName())
                .email(source.getEmail())
                .phone(source.getPhone())
                .transportList(source.getTransportList().stream().map(converter::convert).toList())
                .build();
    }
}
