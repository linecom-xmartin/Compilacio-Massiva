package com.linecom.compilacionmasiva.bbva.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linecom.compilacionmasiva.bbva.entity.DesGrupoFuncional;

@Repository
public interface DesGrupoFuncionalRepository extends JpaRepository<DesGrupoFuncional, String> {

	List<DesGrupoFuncional> findByNombreGrupoFuncional(String nombreGrupoFuncional);

}
