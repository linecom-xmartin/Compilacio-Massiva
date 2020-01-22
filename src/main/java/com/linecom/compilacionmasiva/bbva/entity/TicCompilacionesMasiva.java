package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the TIC_COMPILACIONES_MASIVA database table.
 * 
 */
@Entity
@Table(name="TIC_COMPILACIONES_MASIVA")
@NamedQuery(name="TicCompilacionesMasiva.findAll", query="SELECT t FROM TicCompilacionesMasiva t")
@SqlResultSetMapping(name="paquetsCompilar",
		classes= {
			@ConstructorResult(
				targetClass=com.linecom.compilacionmasiva.bbva.entity.TicCompilacionesMasiva.class,
				columns= {
					@ColumnResult(name="entidad", type=BigDecimal.class),
					@ColumnResult(name="entorno", type=String.class),
					@ColumnResult(name="nombreGrupoFuncional", type=String.class),
					@ColumnResult(name="usuario", type=BigDecimal.class),
					@ColumnResult(name="tipoGrupoFuncional", type=BigDecimal.class),
					@ColumnResult(name="version", type=BigDecimal.class),
					@ColumnResult(name="revision", type=BigDecimal.class),
					@ColumnResult(name="fuente", type=String.class),
					@ColumnResult(name="tipoFuente", type=String.class),
					@ColumnResult(name="fechaHoraPeticion", type=Date.class),
					@ColumnResult(name="resultadoCompilacion", type=BigDecimal.class),
					@ColumnResult(name="fechaHoraUltimoEstado", type=Date.class),
					@ColumnResult(name="ficheroFuente", type=byte[].class),
					@ColumnResult(name="resultado", type=byte[].class),
					@ColumnResult(name="compFullCheckSn", type=String.class),
					@ColumnResult(name="paquete", type=BigDecimal.class)
				})
		})
public class TicCompilacionesMasiva implements Serializable {
	private static final long serialVersionUID = 1L;

	public TicCompilacionesMasiva() {
	}
	
	public TicCompilacionesMasiva(BigDecimal entidad, String entorno, String nombreGrupoFuncional, BigDecimal usuario,
			BigDecimal tipoGrupoFuncional, BigDecimal version, BigDecimal revision, String fuente, String tipoFuente, Date fechaHoraPeticion,
			BigDecimal resultadoCompilacion, Date fechaHoraUltimoEstado, byte[] ficheroFuente, byte[] resultado, String compFullCheckSn, BigDecimal paquete) {
		TicCompilacionesId id = new TicCompilacionesId();
		id.setEntidad(entidad);
		id.setEntorno(entorno);
		id.setNombreGrupoFuncional(nombreGrupoFuncional);
		this.ticCompilacionesId = id;
		this.usuario = usuario;
		this.tipoGrupoFuncional = tipoGrupoFuncional;
		this.version = version;
		this.revision = revision;
		this.fuente = fuente;
		this.tipoFuente = tipoFuente;
		this.fechaHoraPeticion = fechaHoraPeticion;
		this.resultadoCompilacion = resultadoCompilacion;
		this.fechaHoraUltimoEstado = fechaHoraUltimoEstado;
		this.ficheroFuente = ficheroFuente;
		this.resultado = resultado;
		this.compFullChekSn = compFullCheckSn;
		this.paquete = paquete;
	}
	
	@Column(name="COMP_FULL_CHEK_SN")
	private String compFullChekSn;

	@EmbeddedId
	private TicCompilacionesId ticCompilacionesId;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_HORA_PETICION")
	private Date fechaHoraPeticion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_HORA_ULTIMO_ESTADO")
	private Date fechaHoraUltimoEstado;

	@Lob
	@Column(name="FICHERO_FUENTE")
	private byte[] ficheroFuente;

	private String fuente;

	private BigDecimal paquete;

	@Lob
	private byte[] resultado;

	@Column(name="RESULTADO_COMPILACION")
	private BigDecimal resultadoCompilacion;

	private BigDecimal revision;

	@Column(name="TIPO_FUENTE")
	private String tipoFuente;

	@Column(name="TIPO_GRUPO_FUNCIONAL")
	private BigDecimal tipoGrupoFuncional;

	private BigDecimal usuario;

	@Column(name="VERSION")
	private BigDecimal version;

	public String getCompFullChekSn() {
		return this.compFullChekSn;
	}

	public void setCompFullChekSn(String compFullChekSn) {
		this.compFullChekSn = compFullChekSn;
	}

	public Date getFechaHoraPeticion() {
		return this.fechaHoraPeticion;
	}

	public void setFechaHoraPeticion(Date fechaHoraPeticion) {
		this.fechaHoraPeticion = fechaHoraPeticion;
	}

	public Date getFechaHoraUltimoEstado() {
		return this.fechaHoraUltimoEstado;
	}

	public void setFechaHoraUltimoEstado(Date fechaHoraUltimoEstado) {
		this.fechaHoraUltimoEstado = fechaHoraUltimoEstado;
	}

	public byte[] getFicheroFuente() {
		return this.ficheroFuente;
	}

	public void setFicheroFuente(byte[] ficheroFuente) {
		this.ficheroFuente = ficheroFuente;
	}

	public String getFuente() {
		return this.fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public BigDecimal getPaquete() {
		return this.paquete;
	}

	public void setPaquete(BigDecimal paquete) {
		this.paquete = paquete;
	}

	public byte[] getResultado() {
		return this.resultado;
	}

	public void setResultado(byte[] resultado) {
		this.resultado = resultado;
	}

	public BigDecimal getResultadoCompilacion() {
		return this.resultadoCompilacion;
	}

	public void setResultadoCompilacion(BigDecimal resultadoCompilacion) {
		this.resultadoCompilacion = resultadoCompilacion;
	}

	public BigDecimal getRevision() {
		return this.revision;
	}

	public void setRevision(BigDecimal revision) {
		this.revision = revision;
	}

	public String getTipoFuente() {
		return this.tipoFuente;
	}

	public void setTipoFuente(String tipoFuente) {
		this.tipoFuente = tipoFuente;
	}

	public BigDecimal getTipoGrupoFuncional() {
		return this.tipoGrupoFuncional;
	}

	public void setTipoGrupoFuncional(BigDecimal tipoGrupoFuncional) {
		this.tipoGrupoFuncional = tipoGrupoFuncional;
	}

	public BigDecimal getUsuario() {
		return this.usuario;
	}

	public void setUsuario(BigDecimal usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}
	
	public TicCompilacionesId getTicCompilacionesId() {
		return ticCompilacionesId;
	}

	public void setTicCompilacionesId(TicCompilacionesId ticCompilacionesId) {
		this.ticCompilacionesId = ticCompilacionesId;
	}

}