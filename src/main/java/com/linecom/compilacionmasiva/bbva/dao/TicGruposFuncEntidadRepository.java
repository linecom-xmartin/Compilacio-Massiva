package com.linecom.compilacionmasiva.bbva.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linecom.compilacionmasiva.bbva.entity.TicGruposFuncEntidad;
import com.linecom.compilacionmasiva.bbva.entity.TicGruposFuncEntidadId;

@Repository
public interface TicGruposFuncEntidadRepository extends JpaRepository<TicGruposFuncEntidad, TicGruposFuncEntidadId> {

}
