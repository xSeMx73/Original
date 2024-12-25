package ru.sem.clientbase.transport.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import ru.sem.clientbase.client.model.Client;


@Entity
@Table(name = "transport")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String brandName;

    String model;

    String addInform;

    String vin;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    Client owner;

    String gosNumber;

    Integer year;
}
