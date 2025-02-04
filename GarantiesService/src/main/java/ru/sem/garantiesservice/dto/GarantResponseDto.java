package ru.sem.garantiesservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.sem.garantiesservice.model.GarantRequest;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class GarantResponseDto {

    Long id;

    String clientName;

    String partArticle;

    String partName;

    String partBrand;

    String partDealer;

    GarantRequest.Status status;

    LocalDate createDate;

    LocalDate lastUpdateStatusTime;

    String manager;

}
