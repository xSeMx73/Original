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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "garant_seq_gen")
    @SequenceGenerator(name = "garant_seq_gen", sequenceName = "garant_seq", allocationSize = 1)
    Long id;

    String clientName; // Наименование клиента

    Long clientPhone; //номер телефона клиента

    String transportModel; // модель ТС

    String transportBrand; // производитель ТС

    Integer transportYear; //год выпуска ТС

    String gosNumber; // гос номер ТС

    Long mileageStart; // пробег при установке

    Long mileageEnd; // пробег при снятии

    String vin; //вин номер

    LocalDate dateStartRepair; // дата установки запчасти

    LocalDate dateRemovePart; // дата демонтажа запчасти

    String partArticle; // артикул запчасти

    String partBrand; // производитель запчасти

    String partName; // наименование запчасти

    String partDealer; // поставщик запчасти

    String faultDescription; //описание неисправности

    LocalDate createDate; // дата создания рекламации

    String createManager; // ответственный менеджер

    @Enumerated(EnumType.STRING)
    Status status; // статус

    LocalDate lastUpdateStatusTime; // дата последнего обновления статуса

    public enum Status {CREATED, SENDREQUESTDEALER, SENDPARTDEALER, APPROVED, REJECTED, REQUESTMOREINFO}
}



