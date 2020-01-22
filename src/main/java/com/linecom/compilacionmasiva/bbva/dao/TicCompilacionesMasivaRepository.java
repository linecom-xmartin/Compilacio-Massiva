package com.linecom.compilacionmasiva.bbva.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linecom.compilacionmasiva.bbva.entity.TicCompilacionesId;
import com.linecom.compilacionmasiva.bbva.entity.TicCompilacionesMasiva;

@Repository
public interface TicCompilacionesMasivaRepository extends JpaRepository<TicCompilacionesMasiva, TicCompilacionesId> {

	long countByResultadoCompilacion(BigDecimal resultatCompilacio);

	List<TicCompilacionesMasiva> findByResultadoCompilacion(BigDecimal resultadoCompilacion);

	List<TicCompilacionesMasiva> findByTicCompilacionesIdEntornoAndResultadoCompilacion(String entorno, BigDecimal resultadoCompilacion);

}
