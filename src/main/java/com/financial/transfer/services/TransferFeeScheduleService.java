package com.financial.transfer.services;

import com.financial.transfer.models.TransferFeeScheduleModel;
import com.financial.transfer.repositories.TransferFeeScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransferFeeScheduleService {
    @Autowired
    TransferFeeScheduleRepository transferFeeScheduleRepository;

    public Page<TransferFeeScheduleModel> findAll(Pageable pageable) {
        return transferFeeScheduleRepository.findAll(pageable);
    }

    public String calculate(int days, BigDecimal amount){
        Optional<TransferFeeScheduleModel> schedule =
                transferFeeScheduleRepository.findByStartDaysLessThanEqualAndEndDaysGreaterThanEqual(days, days);

        if(schedule.isEmpty()){
            return "Não é nenhuma tarifa";
        }else{
            BigDecimal percentualFee = schedule.get().getPercentualFee();
            System.out.print(schedule.get());
            BigDecimal fullFee = percentualFee.multiply(amount).add(schedule.get().getFixedFee());

            return "O valor da tarifa é de "+ fullFee;
        }

    }
}
