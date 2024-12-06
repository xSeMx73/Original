package ru.sem.clientbase.transport.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.clientbase.transport.dto.TransportResponseDto;
import ru.sem.clientbase.transport.model.Transport;

@Component
public class TransportResponseDtoToTransport implements Converter<TransportResponseDto, Transport> {

    @Override
    public Transport convert(TransportResponseDto source) {
         Transport transport = new Transport();
         transport.setBrandName(source.getBrandName());
         transport.setModel(source.getModel());
         transport.setVin(source.getVin());
         transport.setYear(source.getYear());
         transport.setGosNumber(source.getGosNumber());
         transport.setAddInform(source.getAddInform());
         return transport;
    }
}
