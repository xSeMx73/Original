package ru.sem.gateway.client;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;


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
    @NotBlank
    String nickName;

    Long phone;

    @Email
    String email;
}
