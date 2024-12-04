package ru.sem.clientbase.transport.model.converter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.clientbase.transport.dto.TransportResponseDto;
import ru.sem.clientbase.transport.model.Transport;


@Component
public class TransportToTransportResponseDto implements Converter<Transport, TransportResponseDto>{
    @Override
    public TransportResponseDto convert(Transport source) {
        return TransportResponseDto.builder()
                .brandName(source.getBrandName())
                .model(source.getModel())
                .addInform(source.getAddInform())
                .vin(source.getVin())
                .gosNumber(source.getGosNumber())
                .year(source.getYear())
                .build();
    }
}
