package com.financial.transfer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "TB_TRANSFER")
public class TransferModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    String sourceAccount;
    @Column(nullable = false)
    String destinationAccount;
    @Column(nullable = false)
    BigDecimal transferAmount;

    @Column(nullable = false)
    BigDecimal appliedFee;
    @Column(nullable = false)
    LocalDateTime transferDate;
    @Column(nullable = false)
    private LocalDateTime schedulingDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TransferFeeScheduleModel feeSchedule;
}
