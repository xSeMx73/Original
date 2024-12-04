package ru.sem.clientbase.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sem.clientbase.transport.model.Transport;

public interface TransportRepository extends JpaRepository<Transport,Long> {


}
