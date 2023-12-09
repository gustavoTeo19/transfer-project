package com.financial.transfer.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "TB_TRANSFER_FEE_SCHEDULE")
public class TransferFeeScheduleModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "start_days", nullable = false)
    private Integer startDays;

    @Column(name = "end_days", nullable = false)
    private Integer endDays;

    @Column(name = "fixed_fee", nullable = false)
    private BigDecimal fixedFee;

    @Column(name = "percentual_fee", nullable = false)
    private BigDecimal percentualFee;

    @Override
    public String toString() {
        return "TransferFeeScheduleModel{" +
                "id=" + id +
                ", startDays=" + startDays +
                ", endDays=" + endDays +
                ", fixedFee=" + fixedFee +
                ", percentualFee=" + percentualFee +
                '}';
    }
}
