package com.linecom.compilacionmasiva.bbva.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linecom.compilacionmasiva.bbva.entity.TicPaquetesEntidad;
import com.linecom.compilacionmasiva.bbva.entity.TicPaquetesEntidadId;

@Repository
public interface TicPaquetesEntidadRepository extends JpaRepository<TicPaquetesEntidad, TicPaquetesEntidadId> {

}
