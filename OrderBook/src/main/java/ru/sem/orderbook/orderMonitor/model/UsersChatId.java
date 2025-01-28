package ru.sem.orderbook.orderMonitor.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users_chat_id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@Entity
public class UsersChatId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long chatId;

    String userLogin;

    String userName;

}
