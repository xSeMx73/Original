package ru.sem.garantiesservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Table(name = "garant")
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GarantRequest {

    @Id
    @GeneratedValue
    Long id;

    String clientName;

    Integer clientPhone;

    String transportModel; // модель ТС

    String transportBrand; // производитель ТС

    Integer transportYear; //год выпуска ТС

    String gosNumber; // гос номер ТС

    Long mileageStart; // пробег при установке

    Long mileageEnd; // пробег при снятии

    String vin;

    LocalDate dateStartRepair; // дата установки запчасти

    LocalDate dateRemovePart; // дата демонтажа

    String partArticle;

    String partBrand;

    String partName;

    String partDealer; // поставщик

    String faultDescription; //описание неисправности

    LocalDate createDate; // дата создания рекламации

    String createManager; // ответственный менеджер

    @Enumerated(EnumType.STRING)
    Status status = Status.CREATED; // статус

    LocalDate dateUpdateStatus; // дата последнего обновления статуса

    public enum Status {CREATED, SENDREQUESTDEALER, SENDPARTDEALER, APPROVED, REJECTED, REQUESTMOREINFO}
}



