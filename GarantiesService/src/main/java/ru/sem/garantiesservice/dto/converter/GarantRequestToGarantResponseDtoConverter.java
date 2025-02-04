package ru.sem.garantiesservice.dto.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.garantiesservice.dto.GarantResponseDto;
import ru.sem.garantiesservice.model.GarantRequest;

@RequiredArgsConstructor
@Component
public class GarantRequestToGarantResponseDtoConverter implements Converter<GarantRequest, GarantResponseDto> {
    @Override
    public GarantResponseDto convert(GarantRequest source) {
        return GarantResponseDto.builder()
                .id(source.getId())
                .clientName(source.getClientName())
                .partArticle(source.getPartArticle())
                .partName(source.getPartName())
                .partBrand(source.getPartBrand())
                .partDealer(source.getPartDealer())
                .status(source.getStatus())
                .createDate(source.getCreateDate())
                .manager(source.getCreateManager())
                .build();
    }
}
