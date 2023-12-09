package com.financial.transfer.controllers;

import com.financial.transfer.dtos.CalculateDTO;
import com.financial.transfer.dtos.TransferDTO;
import com.financial.transfer.repositories.TransferRepository;
import com.financial.transfer.services.TransferFeeScheduleService;
import com.financial.transfer.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transfer")
public class TransferController {
    @Autowired
    TransferService transferService;

    @Autowired
    TransferFeeScheduleService transferFeeScheduleService;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid TransferDTO body){
//        Product newProduct = new Product(body);

//        this.repository.save(newProduct);
        transferService.save();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list-transfer-fee-schedule")
    public ResponseEntity getTransferFeeSchedule(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(transferFeeScheduleService.findAll(pageable));
    }

    @PostMapping("/calculete-transfer")
    public ResponseEntity calculete(@RequestBody @Valid CalculateDTO body){
        return ResponseEntity.status(HttpStatus.OK).body(transferFeeScheduleService.calculate(body.days(), body.transferAmount()));
    }

}
