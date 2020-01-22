package com.linecom.compilacionmasiva.bbva.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linecom.compilacionmasiva.bbva.entity.DesGruposFuncPaquete;
import com.linecom.compilacionmasiva.bbva.entity.DesGruposFuncPaqueteId;

@Repository
public interface DesGruposFuncPaqueteRepository extends JpaRepository<DesGruposFuncPaquete, DesGruposFuncPaqueteId> {

}
