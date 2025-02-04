package ru.sem.gateway.garanties;

import lombok.*;
import lombok.experimental.FieldDefaults;

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

    String status;

    LocalDate createDate;

    LocalDate lastUpdateStatusTime;

    String createManager;

}
