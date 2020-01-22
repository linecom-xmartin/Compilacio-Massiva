package com.linecom.compilacionmasiva.bbva.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linecom.compilacionmasiva.bbva.entity.DesPaquete;

@Repository
public interface DesPaqueteRepository extends JpaRepository<DesPaquete, BigDecimal> {

}
