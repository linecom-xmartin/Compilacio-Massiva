package com.linecom.compilacionmasiva.bbva.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql({"/sql/test-schema.sql"})
//@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
class CompMassServiceTest {
	
//	@Configuration
//    static class ContextConfiguration {
//
//        // this bean will be injected into the OrderServiceTest class
//        @Bean
//        public CompMassService compMassService() {
//        	CompMassService compMassService = new CompMassService();
//            // set properties, etc.
//            return compMassService;
//        }
//    }

	
	@Autowired
	private CompMassService compMassService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void testCarregarPaquetsACompilarSensePaquets() {
		//TODO: EJECUCION SIN DATOS --> ok --> TIC_COMPILACIONES_MASIVA EMPTY
		//EJECUCION CON DATOS --> check resultados n EN TIC_COMPILACIONES_MASIVA
		//PROCESO FALLA, MOCK Repository?? (Últim)
		int numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(0, numResultats);
		compMassService.carregarPaquetsACompilar();
		numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(0, numResultats);
	}
	
	@Test
	@Sql({"/sql/test-paquets-a-compilar-data.sql"})
	void testCarregarPaquetsACompilarAmbPaquets() {
		int numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(0, numResultats);
		compMassService.carregarPaquetsACompilar();
		numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(3, numResultats);
	}

	@Test
	@Sql({"/sql/test-paquets-a-compilar-data.sql"})
	void testEliminarTicCompilacioMassiva() {
		//EJECUCION SIN DATOS --> ok --> TIC_COMPILACIONES_MASIVA EMPTY
		//EJECUCION CON DATOS (Carreguem amb carregarPaquetsACompilar)--> ok --> TIC_COMPILACIONES_MASIVA EMPTY
		int numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(0, numResultats);
		compMassService.eliminarTicCompilacioMassiva();
		numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(0, numResultats);
		compMassService.carregarPaquetsACompilar();
		numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(3, numResultats);
		compMassService.eliminarTicCompilacioMassiva();
		numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(0, numResultats);
		
	}

	@Test
	@Sql({"/sql/test-carregar-a-compilacions-data.sql"})
	void testCarregarACompilacions() {
		//ELEMENTS a TIC_COMPILACIONES_MASIVA AMB:
		//RESULTADO_COMPILACION == 0 i <> 0
		//ENTORNO PRODUCCIO i DESENVOLUPAMENT
		//ENTIDAD qualsevol valor
		int numResultatsMasiva = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(7,numResultatsMasiva);
		int numResultatsComp = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES",Integer.class);
		int numResultatsCompResultatCompilacion0 = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES WHERE RESULTADO_COMPILACION = 0",Integer.class);
		assertEquals(7,numResultatsComp);
		assertEquals(1,numResultatsCompResultatCompilacion0);
		assertEquals(6, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(0));
		//ELEMENTS a TIC_COMPILACIONES
		//(1) NO EXISTEIX A TIC_COMPILACIONES_MASIVA --> No s'actualitza
		//(1) VERSION > QUE LA DE TIC_COMPILACIONES_MASIVA I REVISION < --> No s'actualitza
		//(1) VERSION == Q TIC_COMPILACIONES_MASIVA i REVISION > -->No s'actualitza
		//(1) VERSION i REVISION IGUAL TCMASIVA -->s'actualitza --> S'actualitza
		//(1) VERSION <  Q TIC_COMPILACIONES_MASIVA i REVISION > --> S'actualitza
		//(1) VERSION <  Q TIC_COMPILACIONES_MASIVA i REVISION < --> S'actualitza
		//(1) VERSION <  Q TIC_COMPILACIONES_MASIVA i REVISION < PER RESULTADO_COMPILACION <> 0 --> No s'actualitza
		//(1) NO EXISTEIX A TIC_COMPILACIONES --> S'actualitza
		compMassService.carregarACompilacions();
		numResultatsMasiva = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(7,numResultatsMasiva);
		numResultatsComp = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES",Integer.class);
		numResultatsCompResultatCompilacion0 = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES WHERE RESULTADO_COMPILACION = 0",Integer.class);
		assertEquals(8,numResultatsComp); //1+ que no existia
		assertEquals(5,numResultatsCompResultatCompilacion0); //4+ actualitzats
		assertEquals(6, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(0));
		//TOTS ELS VALORS SON ENTIDAD = 9999 i ENTORNO <> 'PRODUCCIO' 
		numResultatsComp = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES WHERE ENTIDAD=9999 AND ENTORNO <>'PRODUCCIO'",Integer.class);
		assertEquals(8,numResultatsComp); //1+ que no existia
	}

	@Test
	@Sql({"/sql/test-compilaciones-data.sql"})
	void testElimiarTicCompilacionesPerUsuari() {
		int numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES",Integer.class);
		assertEquals(5, numResultats);
		try {
			compMassService.elimiarTicCompilacionesPerUsuari(null);
			fail("Paràmetre null incorrecte");
		} catch (IllegalArgumentException ex) {
			numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES",Integer.class);
			assertEquals(5, numResultats);
		}
		numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES",Integer.class);
		assertEquals(5, numResultats);
		compMassService.elimiarTicCompilacionesPerUsuari("123456");
		numResultats = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES",Integer.class);
		assertEquals(2, numResultats);
	}

	@Test
	@Sql({"/sql/test-compilaciones-masiva-data.sql", "/sql/test-compilaciones-data.sql"})
	void testActualitzarTicCompilacioMassiva() {
		//Mirar resultat_compilacio
		assertEquals(1, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(0));
		assertEquals(0, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(1));
		assertEquals(3, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2));
		assertEquals(4, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null)); //TOTS
		compMassService.actualitzarTicCompilacioMassiva();
		assertEquals(0, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(0));
		assertEquals(1, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(1));
		assertEquals(3, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2));
		assertEquals(4, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null)); //TOTS
	}

	@Test
	@Sql({"/sql/test-compilaciones-masiva-data.sql"})
	void testObtenirTotalTicCompilacioMassivaPerResultatCompilacio() {
		assertEquals(1, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(0));
		assertEquals(0, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(1));
		assertEquals(3, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2));
		assertEquals(4, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null)); //TOTS
	}

	@Test
	@Sql({"/sql/test-compilaciones-masiva-data.sql"})
	void testVeureTicCompilacioMassivaPerResultatCompilacio() {
		//NO RESULTATS
		assertTrue(compMassService.veureTicCompilacioMassivaPerResultatCompilacio(1).isEmpty());
		//RESULTATS
		assertFalse(compMassService.veureTicCompilacioMassivaPerResultatCompilacio(2).isEmpty());
		assertEquals(3,compMassService.veureTicCompilacioMassivaPerResultatCompilacio(2).size());
		//TOTS
		assertFalse(compMassService.veureTicCompilacioMassivaPerResultatCompilacio(null).isEmpty());
		assertEquals(4,compMassService.veureTicCompilacioMassivaPerResultatCompilacio(null).size());
	}

	@Test
	@Sql({"/sql/test-compilar-paquets-data.sql"})
	void testCompilarPaquets() {
		int numResultatsPaquetes = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_PAQUETES",Integer.class);
		int numResultatsDGFP = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_GRUPOS_FUNC_PAQUETE",Integer.class);
		int numResultatsTPE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_PAQUETES_ENTIDAD",Integer.class);
		int numResultatsTGFE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_GRUPOS_FUNC_ENTIDAD",Integer.class);
		int numResultatsTicCompMas = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(4,numResultatsTicCompMas);
		assertEquals(3, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2));
		assertEquals(0,numResultatsPaquetes);
		assertEquals(0,numResultatsDGFP);
		assertEquals(0,numResultatsTPE);
		assertEquals(0,numResultatsTGFE);
		try {
			compMassService.compilarPaquets(null);
			fail("Paràmetre null incorrecte");
		} catch (IllegalArgumentException ex) {
			numResultatsPaquetes = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_PAQUETES",Integer.class);
			numResultatsDGFP = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_GRUPOS_FUNC_PAQUETE",Integer.class);
			numResultatsTPE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_PAQUETES_ENTIDAD",Integer.class);
			numResultatsTGFE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_GRUPOS_FUNC_ENTIDAD",Integer.class);
			numResultatsTicCompMas = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
			assertEquals(4,numResultatsTicCompMas);
			assertEquals(3, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2));
			assertEquals(0,numResultatsPaquetes);
			assertEquals(0,numResultatsDGFP);
			assertEquals(0,numResultatsTPE);
			assertEquals(0,numResultatsTGFE);
		}
		try {
			compMassService.compilarPaquets("");
			fail("Paràmetre buit incorrecte");
		} catch (IllegalArgumentException ex) {
			numResultatsPaquetes = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_PAQUETES",Integer.class);
			numResultatsDGFP = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_GRUPOS_FUNC_PAQUETE",Integer.class);
			numResultatsTPE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_PAQUETES_ENTIDAD",Integer.class);
			numResultatsTGFE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_GRUPOS_FUNC_ENTIDAD",Integer.class);
			numResultatsTicCompMas = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
			assertEquals(4,numResultatsTicCompMas);
			assertEquals(3, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2));
			assertEquals(0,numResultatsPaquetes);
			assertEquals(0,numResultatsDGFP);
			assertEquals(0,numResultatsTPE);
			assertEquals(0,numResultatsTGFE);
		}
		numResultatsPaquetes = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_PAQUETES",Integer.class);
		numResultatsDGFP = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_GRUPOS_FUNC_PAQUETE",Integer.class);
		numResultatsTPE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_PAQUETES_ENTIDAD",Integer.class);
		numResultatsTGFE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_GRUPOS_FUNC_ENTIDAD",Integer.class);
		numResultatsTicCompMas = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
		assertEquals(4,numResultatsTicCompMas);
		assertEquals(3, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2));
		assertEquals(0,numResultatsPaquetes);
		assertEquals(0,numResultatsDGFP);
		assertEquals(0,numResultatsTPE);
		assertEquals(0,numResultatsTGFE);
		//SQL VALORS AMB
		//RESULTADO_COMPILACION == 2 i <>2
		//ENTORNO == 'PRODUCCIO' i <> 'PRODUCCIO'
		//(1)RESULTADO_COMPILACION == 1 ENTORNO == 'PRODUCCIO' --> NO INSERTS
		//(1)RESULTADO_COMPILACION == 2 ENTORNO == 'ETIC' --> NO INSERTS
		//(2)RESULTADO_COMPILACION == 2 ENTORNO == 'PRODUCCIO' --> INSERTS
		//Check INSERTs (2) 
		//DES_PAQUETES (2) Nombre_paquete = &Nombre 
		//DES_GRUPOS_FUNC_PAQUETE (2)
		//TIC_PAQUETES_ENTIDAD (2)
		//TIC_GRUPOS_FUNC_ENTIDAD (2)
		//TIPO_GRUPO_FUNCIONAL 2 i 3
		//Nombre= "Funcion_NombreProyecto_RESTO"
		//Nombre= "Batch_NombreProyecto_EXPLO"
		//TODO: SEQ a TEST??
//		compMassService.compilarPaquets("NOM_PROJECTE");
//		numResultatsPaquetes = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_PAQUETES",Integer.class);
//		numResultatsDGFP = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_GRUPOS_FUNC_PAQUETE",Integer.class);
//		numResultatsTPE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_PAQUETES_ENTIDAD",Integer.class);
//		numResultatsTGFE = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_GRUPOS_FUNC_ENTIDAD",Integer.class);
//		numResultatsTicCompMas = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TIC_COMPILACIONES_MASIVA",Integer.class);
//		assertEquals(4,numResultatsTicCompMas);
//		assertEquals(3, compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2));
//		assertEquals(2,numResultatsPaquetes);
//		assertEquals(2,numResultatsDGFP);
//		assertEquals(2,numResultatsTPE);
//		assertEquals(2,numResultatsTGFE);
//		String nombrePaquete1 = "Funcion_NOM_PROJECTE_RESTO";
//		String nombrePaquete2 = "Batch_NOM_PROJECTE_EXPLO";
//		int numResultatsPerNomProjecte1 = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_PAQUETES WHERE NOMBRE_PAQUETE = '" + nombrePaquete1 + "'",Integer.class);
//		int numResultatsPerNomProjecte2 = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM DES_PAQUETES WHERE NOMBRE_PAQUETE = '" + nombrePaquete2 + "'",Integer.class);
//		assertEquals(1,numResultatsPerNomProjecte1);
//		assertEquals(1,numResultatsPerNomProjecte2);
	}

}
