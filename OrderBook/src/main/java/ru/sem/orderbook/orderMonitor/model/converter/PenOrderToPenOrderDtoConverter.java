package ru.sem.orderbook.orderMonitor.model.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.orderbook.orderMonitor.dto.PendOrderDto;
import ru.sem.orderbook.orderMonitor.model.PendOrder;

@RequiredArgsConstructor
@Component
public class PenOrderToPenOrderDtoConverter implements Converter<PendOrder, PendOrderDto> {

    @Override
    public PendOrderDto convert(PendOrder source) {
        return PendOrderDto.builder()
                .id(source.getId())
                .article(source.getArticle())
                .brand(source.getBrand())
                .info(source.getInfo())
                .price(source.getPrice())
                .dealer(source.getDealer())
                .inputData(source.getInputData().toLocalDate())
                .manager(source.getManager())
                .productName(source.getProductName())
                .quantity(source.getQuantity())
                .returnDate(source.getReturnData())
                .build();


    }
}
