package com.financial.transfer.controllers;

import com.financial.transfer.dtos.CalculateDTO;
import com.financial.transfer.dtos.TransferDTO;
import com.financial.transfer.models.TransferFeeScheduleModel;
import com.financial.transfer.models.TransferModel;
import com.financial.transfer.repositories.TransferRepository;
import com.financial.transfer.services.TransferFeeScheduleService;
import com.financial.transfer.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("transfer")
public class TransferController {
    @Autowired
    TransferService transferService;

    @Autowired
    TransferFeeScheduleService transferFeeScheduleService;

    @PostMapping
    public ResponseEntity makeATransfer(@RequestBody @Valid TransferDTO body){
        LocalDateTime schedulingDate = LocalDateTime.now(ZoneId.of("UTC"));

        int days = transferFeeScheduleService.getDaysBetween(body.transferDate(), schedulingDate);
        System.out.println(days);
        TransferFeeScheduleModel scheduleFee = transferFeeScheduleService.findSchedule(days);
        if(scheduleFee == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhuma tarifa");
        }
        BigDecimal fullFee = transferFeeScheduleService.calculate(scheduleFee, body.transferAmount());
        if(fullFee == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhuma tarifa");
        }
        TransferModel transferModel = new TransferModel();
        BeanUtils.copyProperties(body, transferModel);
        transferModel.setSchedulingDate(schedulingDate);
        transferModel.setAppliedFee(fullFee);
        transferModel.setFeeSchedule(scheduleFee);
        return ResponseEntity.status(HttpStatus.OK).body(transferService.save(transferModel));
    }

    @GetMapping("/list-transfer")
    public ResponseEntity getTransferchedule(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(transferService.findAll(pageable));
    }
    @GetMapping("/list-transfer-fee-schedule")
    public ResponseEntity getTransferFeeSchedule(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(transferFeeScheduleService.findAll(pageable));
    }

    @PostMapping("/calculete-transfer")
    public ResponseEntity calculete(@RequestBody @Valid CalculateDTO body){
        LocalDateTime schedulingDate = LocalDateTime.now(ZoneId.of("UTC"));

        int days = transferFeeScheduleService.getDaysBetween(body.transferDate(), schedulingDate);
        System.out.println(days);
        TransferFeeScheduleModel scheduleFee = transferFeeScheduleService.findSchedule(days);
        if(scheduleFee == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhuma tarifa");
        }
        BigDecimal fullFee = transferFeeScheduleService.calculate(scheduleFee, body.transferAmount());
        if(fullFee == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nenhuma tarifa");
        }
        return ResponseEntity.status(HttpStatus.OK).body("O valor da tarifa é de "+ fullFee);

    }

}
