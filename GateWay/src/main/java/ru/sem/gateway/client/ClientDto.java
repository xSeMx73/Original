package ru.sem.gateway.client;


import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    Long id;

    String name;

    String lastName;

    String nickName;

    Long phone;

    @Email
    String email;

    String company;
}
