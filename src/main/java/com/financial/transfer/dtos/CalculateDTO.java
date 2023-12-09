package com.financial.transfer.dtos;

import java.math.BigDecimal;

public record CalculateDTO(
        int days,
        BigDecimal transferAmount
) {
}
