package ru.sem.clientbase.client.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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


    @Size(min = 3 , max = 24, message = "Короткое имя")
    String name;
    @Size(min = 3 , max = 24)
    String lastName;
    @Size(min = 3 , max = 24)
    String nickName;

    Long phone;
    @Email
    String email;
}
