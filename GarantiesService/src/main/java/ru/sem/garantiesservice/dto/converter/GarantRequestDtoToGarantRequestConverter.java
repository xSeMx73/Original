package ru.sem.garantiesservice.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.garantiesservice.dto.GarantRequestDto;
import ru.sem.garantiesservice.model.GarantRequest;

@RequiredArgsConstructor
@Component
public class GarantRequestDtoToGarantRequestConverter implements Converter<GarantRequestDto, GarantRequest> {

    @Override
    public GarantRequest convert(GarantRequestDto source) {
        return GarantRequest.builder()
                .clientName(source.getClientName())
                .build();

    }
}
