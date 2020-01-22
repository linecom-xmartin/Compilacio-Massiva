//package com.linecom.compilacionmasiva.bbva.dao;
//
//import static org.junit.Assert.*;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//import com.linecom.compilacionmasiva.bbva.entity.DesGrupoFuncional;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class DesGrupoFuncionalRepositoryTest {
//	
//	@Autowired
//    private TestEntityManager entityManager;
// 
//	@Autowired
//	private DesGrupoFuncionalRepository repository;
//	
//	@Before
//    public void setUp(){
//        // given
//        DesGrupoFuncional dgf = new DesGrupoFuncional();
//        dgf.setAplicacion("APLICACION");
//        dgf.setCorreccionErrorSn("S");
//        dgf.setDescripcion("descripcion");
//        dgf.setDominioAyudas("dominioAyudas");
//        dgf.setFechaAlta(new Date());
//        dgf.setFechaBaja(null);
//        dgf.setFechaCkout(new Date());
//        dgf.setFormatoMensaje(BigDecimal.ONE);
//        dgf.setGrupoDesarrollo("grupoDesarrollo");
//        dgf.setIddescripcion(BigDecimal.ONE);
//        dgf.setModificableSn("S");
//        dgf.setNombreGrupoFuncional("nombreGrupoFuncional");
//        dgf.setTema("tema");
//        dgf.setTipoGrupoFuncional(BigDecimal.ONE);
//        dgf.setUsuarioAlta(BigDecimal.TEN);
//        dgf.setUsuarioBaja(BigDecimal.TEN);
//        dgf.setUsuarioCkout(BigDecimal.TEN);
//
//        entityManager.persist(dgf);
//        
//        DesGrupoFuncional dgf1 = new DesGrupoFuncional();
//        dgf.setAplicacion("APLICACION");
//        dgf.setCorreccionErrorSn("S");
//        dgf.setDescripcion("descripcion");
//        dgf.setDominioAyudas("dominioAyudas");
//        dgf.setFechaAlta(new Date());
//        dgf.setFechaBaja(null);
//        dgf.setFechaCkout(new Date());
//        dgf.setFormatoMensaje(BigDecimal.ONE);
//        dgf.setGrupoDesarrollo("grupoDesarrollo");
//        dgf.setIddescripcion(BigDecimal.ONE);
//        dgf.setModificableSn("S");
//        dgf.setNombreGrupoFuncional("nombreGrupoFuncional1");
//        dgf.setTema("tema");
//        dgf.setTipoGrupoFuncional(BigDecimal.ONE);
//        dgf.setUsuarioAlta(BigDecimal.TEN);
//        dgf.setUsuarioBaja(BigDecimal.TEN);
//        dgf.setUsuarioCkout(BigDecimal.TEN);
//        
//        entityManager.persist(dgf1);
//    }
//	
//	@Test
//	public void whenFindAll_thenReturnList() {
//		List<DesGrupoFuncional> dgfList = repository.findAll();
//		assertFalse(dgfList.isEmpty());
//		assertEquals(dgfList.size(),2);
//	}
//}
