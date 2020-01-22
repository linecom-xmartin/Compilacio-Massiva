package com.linecom.compilacionmasiva.bbva.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linecom.compilacionmasiva.bbva.dao.DesGrupoFuncionalRepository;
import com.linecom.compilacionmasiva.bbva.dao.DesGruposFuncPaqueteRepository;
import com.linecom.compilacionmasiva.bbva.dao.DesPaqueteRepository;
import com.linecom.compilacionmasiva.bbva.dao.TicCompilacionesMasivaRepository;
import com.linecom.compilacionmasiva.bbva.dao.TicCompilacionesRepository;
import com.linecom.compilacionmasiva.bbva.dao.TicGruposFuncEntidadRepository;
import com.linecom.compilacionmasiva.bbva.dao.TicPaquetesEntidadRepository;
//import com.linecom.compilacionmasiva.bbva.dao.TicPaquetesEntidadRepository;
import com.linecom.compilacionmasiva.bbva.entity.DesGrupoFuncional;
import com.linecom.compilacionmasiva.bbva.entity.DesGruposFuncPaquete;
import com.linecom.compilacionmasiva.bbva.entity.DesGruposFuncPaqueteId;
import com.linecom.compilacionmasiva.bbva.entity.DesPaquete;
import com.linecom.compilacionmasiva.bbva.entity.TicCompilaciones;
import com.linecom.compilacionmasiva.bbva.entity.TicCompilacionesId;
import com.linecom.compilacionmasiva.bbva.entity.TicCompilacionesMasiva;
import com.linecom.compilacionmasiva.bbva.entity.TicGruposFuncEntidad;
import com.linecom.compilacionmasiva.bbva.entity.TicGruposFuncEntidadId;
import com.linecom.compilacionmasiva.bbva.entity.TicPaquetesEntidad;
import com.linecom.compilacionmasiva.bbva.entity.TicPaquetesEntidadId;

@Service
public class CompMassService {
	
	private static final BigDecimal ENTIDAD_9999 = new BigDecimal(9999);
	private static final String PRODUCCIO = "PRODUCCIO";
	private static final String DESENVOLUPAMENT = "DESENVOLUPAMENT";
	private static final String PRO = "PRO";
	private static final String EXPLO = "EXPLO";
	private static final String CLIE = "CLIE";
	private static final String SICOM = "SICOM";
	private static final String CTMEDIACIO = "CTMMEDIACIO";
	private static final String ICC_ASSEGU = "ICC-ASSEGU";
	private static final String SIAS = "SIAS";
	private static final String RESTO = "RESTO";

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private TicCompilacionesRepository ticCompilacionesRepository;
	
	@Autowired
	private TicCompilacionesMasivaRepository ticCompilacionesMasivaRepository;
	
	@Autowired
	private DesGrupoFuncionalRepository desGrupoFuncionalRepository;
	
	@Autowired
	private DesPaqueteRepository desPaqueteRepository;
	
	@Autowired
	private DesGruposFuncPaqueteRepository desGruposFuncPaqueteRepository;
	
	@Autowired
	private TicPaquetesEntidadRepository ticPaquetesEntidadRepository;
	
	@Autowired
	private TicGruposFuncEntidadRepository ticGruposFuncEntidadRepository;
	
	@Autowired
	@Value("${usuario.filtro}")
	private String usuari;
	
	@Autowired
	@Value("${sql.paquetes.compilar}")
	private String sqlPaquetsACompilar;

	@Transactional(readOnly = false)
	public void carregarPaquetsACompilar() {
		String sqltst = "Select tgfe.entidad as entidad,tgfe.entorno as entorno,dgf.nombre_grupo_funcional as nombreGrupoFuncional, 11406 as usuario,dgf.tipo_grupo_funcional as tipoGrupoFuncional," +
				"vgf.version as version, vgf.revision as revision, dfu.fuente as fuente,dfu.tipo_fuente as tipoFuente,sysdate as fechaHoraPeticion,0 as resultadoCompilacion," +
				"null as fechaHoraUltimoEstado,null as ficheroFuente, null as resultado, 'S' as compFullCheckSn,0 as paquete " + 
				"from des_grupo_funcional dgf  " + 
				"left join des_funciones df on df.nombre_grupo_funcional = dgf.nombre_grupo_funcional " + 
				"left join des_fuentes dfu on dfu.nombre_grupo_funcional = dgf.nombre_grupo_funcional " + 
				"left join TIC_GRUPO_FUNCIONAL_ENTORNOS tgfe on tgfe.nombre_grupo_funcional =  dgf.nombre_grupo_funcional and tgfe.entorno in ('ETIC','PRODUCCIO') " + 
				"left join des_versiones_gf vgf on vgf.NOMBRE_GRUPO_FUNCIONAL = dgf.nombre_grupo_funcional " + 
				"where dgf.tipo_grupo_funcional in (1,2,3,5) " + 
				"and tgfe.entorno is not null " + 
				"and dfu.tipo_fuente = 'COB' " + 
				"and (df.grupo_funcional_fuente = df.nombre_grupo_funcional or df.nombre_grupo_funcional is null)  " + 
				"and vgf.nombre_grupo_funcional = dgf.nombre_grupo_funcional  " + 
				"and vgf.estado is null  " + 
				"and (vgf.fecha_estado = " + 
				"		(select max(fecha_estado) from " + 
				"				   des_versiones_gf " + 
				"		  where " + 
				"				   nombre_grupo_funcional = " + 
				"				   dgf.nombre_grupo_funcional and " + 
				"				   estado is null) " + 
				"		  or " + 
				"				  vgf.version*100+vgf.revision = " + 
				"				 (select max(version*100+revision) from " + 
				"						 des_versiones_gf " + 
				"				  Where  " + 
				"						 nombre_grupo_funcional = dgf.nombre_grupo_funcional and " + 
				"						 estado is null) " + 
				") " + 
				"order by dgf.nombre_grupo_funcional desc";
		
		//TODO: FICHERO FUENTE???
		@SuppressWarnings("unchecked")
		List<TicCompilacionesMasiva> resultat = em.createNativeQuery(sqltst,"paquetsCompilar").getResultList();
		HashSet<String> nmsGrupFuncional = new HashSet<String>(); 
//		em.getTransaction().begin();
		for (TicCompilacionesMasiva elementAGuardar : resultat) {
			//Evitem guardar registres que no utilitzarem i que poden donar conflicte
			boolean afegit = nmsGrupFuncional.add(elementAGuardar.getTicCompilacionesId().getNombreGrupoFuncional());
			if (afegit) {
				em.persist(elementAGuardar);
				em.flush();
				em.clear();	
			}
		}
//		em.getTransaction().commit();
	}
	
	@Transactional(readOnly = false)
	public void eliminarTicCompilacioMassiva() {
		em.createQuery("DELETE FROM TicCompilacionesMasiva").executeUpdate();	
	}
	
	@Transactional(readOnly = false)
	public void carregarACompilacions() {
		//Obtenim els valors de TIC_COMPILACIONES_MASIVA
		List<TicCompilacionesMasiva> ticCompilacionesMasiva = ticCompilacionesMasivaRepository.findByResultadoCompilacion(new BigDecimal(0));
		//Només 1 per nombre_grupo_funcional
		HashSet<String> nmsGrupFuncional = new HashSet<String>();
		List<TicCompilaciones> ticCompilacionesMasivaAdaptat = new ArrayList<TicCompilaciones>();
		for (TicCompilacionesMasiva ticCompMasiva : ticCompilacionesMasiva) {
			boolean afegit = nmsGrupFuncional.add(ticCompMasiva.getTicCompilacionesId().getNombreGrupoFuncional());
			if (afegit) {
				//Si el camp entorno es PRODUCCIO, el canviem per DESENVOLUPAMENT
				//El camp entidad s'estableix a 9999
				TicCompilaciones ticComp = new TicCompilaciones();
				ticComp = copiarValors(ticCompMasiva);
				ticComp.getTicCompilacionesId().setEntidad(ENTIDAD_9999);
				if (ticCompMasiva.getTicCompilacionesId().getEntorno().equalsIgnoreCase(PRODUCCIO)) {
					ticComp.getTicCompilacionesId().setEntorno(DESENVOLUPAMENT);
				}
				
				//Si existeix
				//	Si Versio i Revisió a TIC_COMPILACIONES es major, no es guarda (primer check versió, després la revisió de la versió)
				//	Si Versio i Revisió a TIC_COMPILACIONES es menor, s'elimina de TIC_COMPILACIONES es guarda (primer check versió, després la revisió de la versió)
				//Si no existeix
				//	guardar
				TicCompilacionesId id = ticComp.getTicCompilacionesId();
				Optional<TicCompilaciones> resultat = ticCompilacionesRepository.findById(id);
				if (resultat.isPresent()) {
					if (ticComp.getVersion().compareTo(resultat.get().getVersion()) == 0) {
						if (ticComp.getRevision().compareTo(resultat.get().getVersion()) >= 0) {
							ticCompilacionesRepository.saveAndFlush(ticComp);	
						} else {
							continue;
						}
					} else if (ticComp.getVersion().compareTo(resultat.get().getVersion()) > 0){
						ticCompilacionesRepository.saveAndFlush(ticComp);
					} else {
						continue;	
					}
				} else {
					ticCompilacionesRepository.saveAndFlush(ticComp);
				}
				ticCompilacionesMasivaAdaptat.add(ticComp);
			}
		}
		//Sense transaction?? (1 a 1)??
//		em.getTransaction().begin();
//		for (TicCompilaciones elementAGuardar : ticCompilacionesMasivaAdaptat) {
//			em.persist(elementAGuardar);
//			em.flush();
//			em.clear();
//		}
//		em.getTransaction().commit();
	}
	
	private TicCompilaciones copiarValors(TicCompilacionesMasiva ticCompMasiva) {
		TicCompilaciones ticCompilaciones = new TicCompilaciones();
		ticCompilaciones.setCompFullChekSn(ticCompMasiva.getCompFullChekSn());
		ticCompilaciones.setFechaHoraPeticion(ticCompMasiva.getFechaHoraPeticion());
		ticCompilaciones.setFechaHoraUltimoEstado(ticCompMasiva.getFechaHoraUltimoEstado());
		ticCompilaciones.setFicheroFuente(ticCompMasiva.getFicheroFuente());
		ticCompilaciones.setResultado(ticCompMasiva.getResultado());
		ticCompilaciones.setResultadoCompilacion(ticCompMasiva.getResultadoCompilacion());
		ticCompilaciones.setRevision(ticCompMasiva.getRevision());
		ticCompilaciones.setTicCompilacionesId(ticCompMasiva.getTicCompilacionesId());
		ticCompilaciones.setTipoFuente(ticCompMasiva.getTipoFuente());
		ticCompilaciones.setTipoGrupoFuncional(ticCompMasiva.getTipoGrupoFuncional());
		ticCompilaciones.setUsuario(ticCompMasiva.getUsuario());
		ticCompilaciones.setVersion(ticCompMasiva.getVersion());
		return ticCompilaciones;
	}
	
	@Transactional(readOnly = false)
	public void elimiarTicCompilacionesPerUsuari(String usuari) {
		if (usuari == null || usuari.isEmpty()) {
			throw new IllegalArgumentException("L'usuari no pot ser null ni buit");
		}
		em.createQuery("DELETE FROM TicCompilaciones t WHERE t.usuario = ?1").setParameter(1,new BigDecimal(usuari)).executeUpdate();
	}

	@Transactional(readOnly = false)
	public List<TicCompilacionesMasiva> actualitzarTicCompilacioMassiva() {
		//Que passa quant no s'ha actualitzat TIC_COMPILACIONES, xq la versió era MAJOR?
		//Ara estem actualitzant igualment TIC_COMPILACIONES_MASIVA
		//Que passa si hi ha +1 amb mateix nombre_grupo_funcional 
		//Ara s'agafa el de versio i revisio mes alt
		List<TicCompilacionesMasiva> ticCompilacionesMasiva = ticCompilacionesMasivaRepository.findAll();
		List<TicCompilacionesMasiva> ticCompilacionesMasivaActualitzat = new ArrayList<TicCompilacionesMasiva>();
		//Obtenim elements a actualitzar
		for (TicCompilacionesMasiva ticCompilaMas : ticCompilacionesMasiva) {
			List<TicCompilaciones> lstTicCompilaciones = ticCompilacionesRepository.findByNombreGrupoFuncionalAndVersionAndRevisionOrderByVersionDescAndRevisionDesc(
				ticCompilaMas.getTicCompilacionesId().getNombreGrupoFuncional(), ticCompilaMas.getRevision(), ticCompilaMas.getVersion());
			if (lstTicCompilaciones != null && !lstTicCompilaciones.isEmpty()) {
				TicCompilaciones ticCompilaciones = lstTicCompilaciones.get(0);
				if(ticCompilaMas.getResultadoCompilacion().compareTo(ticCompilaciones.getResultadoCompilacion()) != 0) {
					ticCompilaMas.setResultadoCompilacion(ticCompilaciones.getResultadoCompilacion());
					ticCompilaMas.setFechaHoraUltimoEstado(ticCompilaciones.getFechaHoraUltimoEstado());
					ticCompilaMas.setResultado(ticCompilaciones.getResultado());
					ticCompilacionesMasivaRepository.saveAndFlush(ticCompilaMas);
					ticCompilacionesMasivaActualitzat.add(ticCompilaMas);	
				}
			}
		}
		return ticCompilacionesMasivaActualitzat;
	}

	@Transactional(readOnly = true)
	public long obtenirTotalTicCompilacioMassivaPerResultatCompilacio(Integer resultatCompilacio) {
		if (resultatCompilacio == null) {
			return ticCompilacionesMasivaRepository.count();
		} else {
			return ticCompilacionesMasivaRepository.countByResultadoCompilacion(new BigDecimal(resultatCompilacio));
		}
	}
	
	@Transactional(readOnly = true)
	public List<TicCompilacionesMasiva> veureTicCompilacioMassivaPerResultatCompilacio(Integer resultatCompilacio) {
		List<TicCompilacionesMasiva> ticCompilacionesMasiva = null;
		if (resultatCompilacio == null) {
			ticCompilacionesMasiva = ticCompilacionesMasivaRepository.findAll();	
		} else {
			ticCompilacionesMasiva = ticCompilacionesMasivaRepository.findByResultadoCompilacion(new BigDecimal(resultatCompilacio));
		}
		
		return ticCompilacionesMasiva;
	}
	
	@Transactional(readOnly = false)
	public int compilarPaquets(String nomProjecte) {
		if (nomProjecte == null || nomProjecte.isEmpty()) {
			throw new IllegalArgumentException("El nom del projecte no pot ser null ni buit");
		}
		List<TicCompilacionesMasiva> ticCompilacionesMasiva = ticCompilacionesMasivaRepository.findByTicCompilacionesIdEntornoAndResultadoCompilacion(PRODUCCIO,new BigDecimal(2));
		String nomAplicacio = "";
		Set<String> nomsGrupoFuncional = new HashSet<String>();
		int numPaquetes = 0;
		for (TicCompilacionesMasiva ticCompMasiva : ticCompilacionesMasiva) {
			boolean nouElement = nomsGrupoFuncional.add(ticCompMasiva.getTicCompilacionesId().getNombreGrupoFuncional());
			if (nouElement) {
				Optional<DesGrupoFuncional> dgf = desGrupoFuncionalRepository.findById(ticCompMasiva.getTicCompilacionesId().getNombreGrupoFuncional());
				String nomApp = "";
				if (dgf.isPresent()) {
					nomApp = dgf.get().getAplicacion();
				}
				nomAplicacio = obtenirNomAplicacio(nomApp.toUpperCase());
				//Afegir registre a DES_PAQUETES
				DesPaquete desPaquete = omplirDadesDesPaquete(nomProjecte,nomAplicacio,ticCompMasiva.getTipoGrupoFuncional(), ticCompMasiva.getTicCompilacionesId().getEntidad());
				desPaquete = desPaqueteRepository.saveAndFlush(desPaquete);
				BigDecimal idPaquete = desPaquete.getPaquete();
				//Afegir registre a DES_GRUPOS_FUNC_PAQUETE
				DesGruposFuncPaquete desGruposFuncPaquete = omplirDadesDesGruposFuncPaquete(idPaquete, ticCompMasiva);
				desGruposFuncPaqueteRepository.saveAndFlush(desGruposFuncPaquete);
				//Afegir registre a TIC_PAQUETES_ENTIDAD
				TicPaquetesEntidad ticPaquetesEntidad = omplirDadesTicPaquetesEntidad(idPaquete,ticCompMasiva.getTicCompilacionesId().getEntidad());
				ticPaquetesEntidadRepository.saveAndFlush(ticPaquetesEntidad);
				//Afegir registre a TIC_GRUPOS_FUNC_ENTIDAD
				TicGruposFuncEntidad ticGruposFuncEntidad = omplirDadesTicGruposFuncEntidad(idPaquete,ticCompMasiva);
				ticGruposFuncEntidadRepository.saveAndFlush(ticGruposFuncEntidad);
				numPaquetes++;
			}
		}
		return numPaquetes;
	}

	private TicGruposFuncEntidad omplirDadesTicGruposFuncEntidad(BigDecimal idPaquete,
			TicCompilacionesMasiva ticCompMasiva) {
		TicGruposFuncEntidad ticGruposFuncEntidad = new TicGruposFuncEntidad();
		ticGruposFuncEntidad.setActivoSn("S");
		ticGruposFuncEntidad.setDescripcionError(null);
		ticGruposFuncEntidad.setDescripcionErrorSistema(null);
		ticGruposFuncEntidad.setEstado(new BigDecimal(3));
		ticGruposFuncEntidad.setFechaEstado(new Date());
		ticGruposFuncEntidad.setFechaEstadoSistema(new Date());
		ticGruposFuncEntidad.setFuente(ticCompMasiva.getFuente());
		ticGruposFuncEntidad.setRevision(ticCompMasiva.getRevision());
		TicGruposFuncEntidadId ticGruposFuncEntidadId = new TicGruposFuncEntidadId();
		ticGruposFuncEntidadId.setEntidad(ticCompMasiva.getTicCompilacionesId().getEntidad());
		ticGruposFuncEntidadId.setNombreGrupoFuncional(ticCompMasiva.getTicCompilacionesId().getNombreGrupoFuncional());
		ticGruposFuncEntidadId.setPaquete(idPaquete);
		ticGruposFuncEntidad.setTicGruposFuncEntidadId(ticGruposFuncEntidadId );
		ticGruposFuncEntidad.setTipoFuente(ticCompMasiva.getTipoFuente());
		ticGruposFuncEntidad.setTipoGrupoFuncional(ticCompMasiva.getTipoGrupoFuncional());
		ticGruposFuncEntidad.setVersion(ticCompMasiva.getVersion());
		return ticGruposFuncEntidad;
	}

	private TicPaquetesEntidad omplirDadesTicPaquetesEntidad(BigDecimal idPaquete, BigDecimal entidad) {
		TicPaquetesEntidad ticPaquetesEntidad = new TicPaquetesEntidad();
		ticPaquetesEntidad.setCompFullChekSn("S");
		ticPaquetesEntidad.setEspecialSn("N");
		ticPaquetesEntidad.setEstado(BigDecimal.ZERO);
		ticPaquetesEntidad.setFechaEstado(new Date());
		ticPaquetesEntidad.setFechaSolicitud(new Date());
		ticPaquetesEntidad.setPublicoSn("N");
		ticPaquetesEntidad.setSimpleSn("N");
		TicPaquetesEntidadId ticPaquetesEntidadId = new TicPaquetesEntidadId();
		ticPaquetesEntidadId.setEntidad(entidad);
		ticPaquetesEntidadId.setPaquete(idPaquete);
		ticPaquetesEntidad.setTicPaquetesEntidadId(ticPaquetesEntidadId );
		ticPaquetesEntidad.setUsuario(new BigDecimal(usuari)); //TODO: PROPERTIES
		return ticPaquetesEntidad;
	}

	private DesGruposFuncPaquete omplirDadesDesGruposFuncPaquete(BigDecimal idPaquete,
			TicCompilacionesMasiva ticCompMasiva) {
		DesGruposFuncPaquete desGruposFuncPaquete = new DesGruposFuncPaquete();
		desGruposFuncPaquete.setActivoSn("S");
		DesGruposFuncPaqueteId desGruposFuncPaqueteId = new DesGruposFuncPaqueteId();
		desGruposFuncPaqueteId.setNombreGrupoFuncional(ticCompMasiva.getTicCompilacionesId().getNombreGrupoFuncional());
		desGruposFuncPaqueteId.setPaquete(idPaquete);
		desGruposFuncPaquete.setDesGruposFuncPaqueteId(desGruposFuncPaqueteId );
		desGruposFuncPaquete.setRevision(ticCompMasiva.getRevision());
		desGruposFuncPaquete.setUsuario(new BigDecimal(usuari));//TODO: PPROPERTIES!!!
		desGruposFuncPaquete.setVersion(ticCompMasiva.getVersion());
		return desGruposFuncPaquete;
	}

	private DesPaquete omplirDadesDesPaquete(String nomProjecte, String nomAplicacio, BigDecimal tipoGrupoFuncional, BigDecimal entidad) {
		// TODO seq_paquetes??
		DesPaquete desPaquete = new DesPaquete();
		String nom = "";
		String descripcio = "";
		if (tipoGrupoFuncional.compareTo(BigDecimal.ONE) == 0) {
			nom = "Rutina_" + nomProjecte + "_" + nomAplicacio;
			descripcio = "Rutinas del proyecto: " + nomProjecte + "_" + nomAplicacio;
		} else if (tipoGrupoFuncional.compareTo(new BigDecimal(2)) == 0) {
			nom = "Batch_" + nomProjecte + "_" + nomAplicacio;
			descripcio =  "Batchs del proyecto: " + nomProjecte + "_" + nomAplicacio;
		} else if (tipoGrupoFuncional.compareTo(new BigDecimal(3)) == 0) {
			nom = "Funcion_" + nomProjecte + "_" + nomAplicacio;
			descripcio =  "Funciones del proyecto: " + nomProjecte + "_" + nomAplicacio; 
		} else if (tipoGrupoFuncional.compareTo(new BigDecimal(5)) == 0) {
			nom = "Generico_" + nomProjecte + "_" + nomAplicacio;
			descripcio =  "Funciones de genéricos del proyecto: " + nomProjecte + "_" + nomAplicacio; 
		}
		desPaquete.setCompFullChekSn("S");
		desPaquete.setDescripcion(descripcio);
		desPaquete.setEntidad(entidad);
		desPaquete.setEspecialSn("N");
		desPaquete.setFecha(new Date());
		desPaquete.setFechaEstado(new Date());
		desPaquete.setFicheroInstalacion(null);
		desPaquete.setGrupoDesarrollo("Sistemes ct*");
		desPaquete.setNombrePaquete(nom);
//		desPaquete.setPaquete(seq??);
		desPaquete.setPublicoSn("N");
		desPaquete.setSimpleSn("N");
		desPaquete.setTipoFicheroInstalacion("");
		desPaquete.setUsuario(new BigDecimal(usuari));//TODO: PER .properties
		
		return desPaquete;
	}

	private String obtenirNomAplicacio(String nomGrupFuncional) {
		String nomAplicacio = "";
		switch (nomGrupFuncional) {
			case PRO:
				nomAplicacio = PRO;
				break;
			case EXPLO:
				nomAplicacio = EXPLO;
				break;
			case CLIE:
				nomAplicacio = CLIE;
				break;
			case SICOM:
				nomAplicacio = SICOM;
				break;
			case CTMEDIACIO:
				nomAplicacio = CTMEDIACIO;
				break;
			case ICC_ASSEGU:
				nomAplicacio = ICC_ASSEGU;
				break;
			case SIAS:
				nomAplicacio = SIAS;
				break;
			default :
				nomAplicacio = RESTO;
				break;
		}
		return nomAplicacio;
	}
}
