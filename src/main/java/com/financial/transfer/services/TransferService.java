package com.financial.transfer.services;

import com.financial.transfer.repositories.TransferFeeScheduleRepository;
import com.financial.transfer.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    TransferRepository transferRepository;


    public Boolean save(){

        return true;
    }


}
