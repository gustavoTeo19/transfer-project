package com.financial.transfer.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CalculateDTO(
        LocalDateTime transferDate,
        BigDecimal transferAmount
) {
}
