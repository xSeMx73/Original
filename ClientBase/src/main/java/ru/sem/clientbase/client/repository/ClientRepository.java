package ru.sem.clientbase.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sem.clientbase.client.model.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select cl from Client as cl where cl.lastName ilike CONCAT('%', :query, '%') " +
            "or cl.name ilike CONCAT('%', :query, '%') " +
            "or cl.nickName ilike CONCAT('%', :query, '%')")
    Client findByQuery(String query);

    @Query("select cl from Client as cl where cl.lastName ilike CONCAT('%', :query, '%') " +
            "or cl.name ilike CONCAT('%', :query, '%') " +
            "or cl.nickName ilike CONCAT('%', :query, '%')")
    List<Client> findAllByQuery(String query);
}
