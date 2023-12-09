package com.financial.transfer.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "TB_TRANSFER_FEE_SCHEDULE")
public class TransferFeeScheduleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Integer startDays;

    @Column(nullable = false)
    private Integer endDays;

    @Column(nullable = false)
    private BigDecimal fixedFee;

    @Column(nullable = false)
    private BigDecimal percentualFee;
}
