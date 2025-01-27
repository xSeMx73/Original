package ru.sem.gateway.orderBook.orderMonitor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Slf4j
@Component
public class OrderMonitorWebClient {

   private final WebClient webClient;
   public String url;

    public OrderMonitorWebClient(@Value("${orderBook.url:http://192.168.1.135:8181}") String url) {
        webClient = WebClient.create(url);
        this.url = url;
    }

    public Flux<PendOrderDto> getPendOrders() {
        log.info("<--- GATEWAY OrderMonitorWebClient Получение заказов на возврат");
        return webClient
                .get()
                .uri(url + "/monitor")
                .retrieve()
                .bodyToFlux(PendOrderDto.class);
    }

    public void setReason(String reason, String id) {
        webClient.post()
                .uri(url + "/monitor")
                .header("reason", reason)
                .header("id", id)
                .retrieve().bodyToMono(String.class)
                .block();
    }
}
