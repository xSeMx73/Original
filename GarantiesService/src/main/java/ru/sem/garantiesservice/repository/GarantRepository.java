package ru.sem.garantiesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sem.garantiesservice.model.GarantRequest;

@Repository
public interface GarantRepository extends JpaRepository<GarantRequest, Long> {
}
