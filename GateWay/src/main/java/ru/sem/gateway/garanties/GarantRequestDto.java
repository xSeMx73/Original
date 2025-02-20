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
public class GarantRequestDto {

    Long id;

    String clientName;

    Long clientPhone;

    String transportModel;

    String transportBrand;

    Integer transportYear;

    String gosNumber;

    Long mileageStart;

    Long mileageEnd;

    String vin;

    LocalDate dateStartRepair;

    LocalDate dateRemovePart;

    String partArticle;

    String partBrand;

    String partName;

    String partDealer;

    String faultDescription;

    LocalDate createDate;

    String createManager;

    String status;

    LocalDate lastUpdateStatusTime;


}
