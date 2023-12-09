package com.financial.transfer.services;

import com.financial.transfer.models.TransferFeeScheduleModel;
import com.financial.transfer.repositories.TransferFeeScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransferFeeScheduleService {
    @Autowired
    TransferFeeScheduleRepository transferFeeScheduleRepository;

    public Page<TransferFeeScheduleModel> findAll(Pageable pageable) {
        return transferFeeScheduleRepository.findAll(pageable);
    }

    public TransferFeeScheduleModel findSchedule(int days){
        Optional<TransferFeeScheduleModel> schedule =
                transferFeeScheduleRepository.findByStartDaysLessThanEqualAndEndDaysGreaterThanEqual(days, days);
        return schedule.orElse(null);
    }

    public BigDecimal calculate(TransferFeeScheduleModel scheduleFee, BigDecimal amount){
        BigDecimal percentualFee = scheduleFee.getPercentualFee();
        System.out.print(scheduleFee);
        return percentualFee.multiply(amount).add(scheduleFee.getFixedFee());
    }

    public int getDaysBetween(LocalDateTime transferDate, LocalDateTime schedulingDate){
        System.out.println(schedulingDate);

        Duration duration = Duration.between(schedulingDate, transferDate);
        long daysLong = duration.toDays();
        if (daysLong > Integer.MAX_VALUE) {
            throw new ArithmeticException("A diferença de dias é muito grande para ser representada como um int.");
        }
        return (int) daysLong;
    }
}
