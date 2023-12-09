package com.financial.transfer.repositories;

import com.financial.transfer.models.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferRepository extends JpaRepository<TransferModel, UUID> {

}
