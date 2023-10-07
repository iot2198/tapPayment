package com.tap.service.data.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;
public interface PayerIdRepository extends CrudRepository<PayerId, UUID> {
}
