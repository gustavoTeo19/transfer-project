package com.financial.transfer.services;

import com.financial.transfer.models.TransferFeeScheduleModel;
import com.financial.transfer.models.TransferModel;
import com.financial.transfer.repositories.TransferFeeScheduleRepository;
import com.financial.transfer.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    @Autowired
    TransferRepository transferRepository;

    @Transactional
    public TransferModel save(TransferModel transferModel){
        return transferRepository.save(transferModel);
    }

    public Page<TransferModel> findAll(Pageable pageable) {
        return transferRepository.findAll(pageable);
    }



}
