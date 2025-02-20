package ru.sem.garantiesservice.dto.converter;


import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.sem.garantiesservice.dto.GarantRequestDto;
import ru.sem.garantiesservice.model.GarantRequest;

@RequiredArgsConstructor
@Component
public class GarantRequestToGarantRequestDtoConverter implements Converter<GarantRequest, GarantRequestDto> {

    @Override
    public GarantRequestDto convert(GarantRequest source) {

        return GarantRequestDto.builder()
                .id(source.getId())
                .clientName(source.getClientName())
                .clientPhone(source.getClientPhone())
                .transportModel(source.getTransportModel())
                .transportBrand(source.getTransportBrand())
                .gosNumber(source.getGosNumber())
                .transportYear(source.getTransportYear())
                .mileageStart(source.getMileageStart())
                .mileageEnd(source.getMileageEnd())
                .vin(source.getVin())
                .dateStartRepair(source.getDateStartRepair())
                .dateRemovePart(source.getDateRemovePart())
                .partArticle(source.getPartArticle())
                .partBrand(source.getPartBrand())
                .partName(source.getPartName())
                .partDealer(source.getPartDealer())
                .createDate(source.getCreateDate())
                .createManager(source.getCreateManager())
                .faultDescription(source.getFaultDescription())
                .status(source.getStatus())
                .lastUpdateStatusTime(source.getLastUpdateStatusTime())
                .build();
    }
}
