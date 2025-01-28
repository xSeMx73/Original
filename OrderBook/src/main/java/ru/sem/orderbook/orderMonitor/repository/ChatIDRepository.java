package ru.sem.orderbook.orderMonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sem.orderbook.orderMonitor.model.UsersChatId;

import java.util.List;

@Repository
public interface ChatIDRepository extends JpaRepository<UsersChatId, Long> {

    @Query("select u.chatId from UsersChatId as u where u.userName ilike :manager")
    Long findByUserName(String manager);


    @Query("select u.userName from UsersChatId as u")
    List<String> findAllNames();

}
