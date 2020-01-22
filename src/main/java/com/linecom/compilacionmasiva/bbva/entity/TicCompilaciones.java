package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for TIC_COMPILACIONES database table.
 * 
 */
@Entity
@Table(name="TIC_COMPILACIONES")
@NamedQueries({
@NamedQuery(name="TicCompilaciones.findAll", query="SELECT t FROM TicCompilaciones t"),
@NamedQuery(name="TicCompilaciones.findByNombreGrupoFuncionalAndVersionAndRevisionOrderByVersionDescAndRevisionDesc", 
	query="SELECT t FROM TicCompilaciones t WHERE t.ticCompilacionesId.nombreGrupoFuncional = :nombreGrupoFuncional and t.version = :version"
			+ " and t.revision = :revision ORDER BY t.version DESC, t.revision DESC")
})
public class TicCompilaciones implements Serializable {
	private static final long serialVersionUID = 1L;

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

	public TicCompilaciones() {
	}

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