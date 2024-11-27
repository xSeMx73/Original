package ru.sem.original;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sem.gateway.GateWayApplication;

@SpringBootApplication
public class OriginalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);

    }

}
