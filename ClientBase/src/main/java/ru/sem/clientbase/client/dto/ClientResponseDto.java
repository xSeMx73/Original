package ru.sem.clientbase.client.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sem.clientbase.transport.dto.TransportResponseDto;

import java.util.List;

@ToString
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto {

    Long id;

    String name;

    String lastName;

    String nickName;

    Long phone;

    String email;

    List<TransportResponseDto> transportList;
}