package com.financial.transfer.controllers;

import com.financial.transfer.dtos.TransferDTO;
import com.financial.transfer.repositories.TransferRepository;
import com.financial.transfer.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class TransferController {
    @Autowired
    TransferService transferService;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid TransferDTO body){
//        Product newProduct = new Product(body);

//        this.repository.save(newProduct);
        transferService.save();
        return ResponseEntity.ok().build();
    }


}
