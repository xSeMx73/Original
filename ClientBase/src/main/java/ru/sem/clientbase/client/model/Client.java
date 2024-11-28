package ru.sem.clientbase.client.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Entity
@Table(name = "clients")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String clientName;

    String clientLastName;

    String clientNickName;

    Integer phone;

    Long organizationId;

    String email;

    Set<Long> transportList;

}
