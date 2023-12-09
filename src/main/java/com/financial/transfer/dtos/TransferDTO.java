package com.financial.transfer.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public record TransferDTO(
                @NotBlank
                String sourceAccount,

                @NotBlank
                String destinationAccount,

                @NotNull
                BigDecimal transferValue,

                @NotNull
                LocalDateTime transferDate
) {
}
