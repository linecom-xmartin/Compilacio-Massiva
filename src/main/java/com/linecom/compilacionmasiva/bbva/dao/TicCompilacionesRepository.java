package com.linecom.compilacionmasiva.bbva.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.linecom.compilacionmasiva.bbva.entity.TicCompilaciones;
import com.linecom.compilacionmasiva.bbva.entity.TicCompilacionesId;

@Repository
public interface TicCompilacionesRepository extends JpaRepository<TicCompilaciones, TicCompilacionesId> {

	List<TicCompilaciones> findByNombreGrupoFuncionalAndVersionAndRevisionOrderByVersionDescAndRevisionDesc(@Param("nombreGrupoFuncional") String nombreGrupoFuncional, 
			@Param("revision") BigDecimal revision,@Param("version") BigDecimal version);

}
