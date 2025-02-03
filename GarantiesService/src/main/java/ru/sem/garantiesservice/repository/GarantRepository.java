package ru.sem.garantiesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sem.garantiesservice.model.GarantRequest;

public interface GarantRepository extends JpaRepository<GarantRequest, Long> {
}
