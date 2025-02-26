package ru.sem.gateway.client;


import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sem.gateway.transport.TransportDto;

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

    String company;

    List<TransportDto> transportList;
}