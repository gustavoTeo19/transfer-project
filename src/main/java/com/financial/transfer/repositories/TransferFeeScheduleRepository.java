package com.financial.transfer.repositories;

import com.financial.transfer.models.TransferFeeScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransferFeeScheduleRepository extends JpaRepository<TransferFeeScheduleModel, UUID> {
    Optional<TransferFeeScheduleModel> findByStartDaysLessThanEqualAndEndDaysGreaterThanEqual(Integer startDays, Integer endDays);

}
