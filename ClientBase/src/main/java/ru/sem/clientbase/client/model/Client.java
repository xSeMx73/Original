package ru.sem.clientbase.client.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.sem.clientbase.transport.model.Transport;

import java.util.List;


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

    String name;

    String lastName;

    String nickName;

    Long phone;

    String email;

    String company;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Transport> transportList;

}
