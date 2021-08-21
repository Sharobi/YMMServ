package com.retail.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.PaymentMode;

@Repository
public interface PaymentModeRepository extends CrudRepository<PaymentMode, Integer> {

}
