package ru.sem.garantiesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sem.garantiesservice.dto.GarantRequestDto;
import ru.sem.garantiesservice.model.GarantRequest;

import java.util.Collection;
import java.util.List;

@Repository
public interface GarantRepository extends JpaRepository<GarantRequest, Long> {


    @Query("select g from GarantRequest as g order by g.lastUpdateStatusTime desc")
    List<GarantRequest> findAllAndSort();
}
