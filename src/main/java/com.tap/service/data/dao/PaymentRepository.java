package com.tap.service.data.dao;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<Payment, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE Payment p SET p.success = true WHERE p.transactionId = :transactionId")
    void update(@Param("transactionId") UUID transactionId);

    @Query("SELECT CASE WHEN COUNT(p1_0.transactionId) > 0 THEN false ELSE true END FROM Payment p1_0 WHERE p1_0.transactionId = :transactionId")
    boolean doesNotExistByTransactionId(@Param("transactionId") UUID transactionId);


}
