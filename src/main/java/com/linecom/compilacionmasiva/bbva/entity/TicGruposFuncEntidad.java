package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TIC_GRUPOS_FUNC_ENTIDAD database table.
 * 
 */
@Entity
@Table(name="TIC_GRUPOS_FUNC_ENTIDAD")
@NamedQuery(name="TicGruposFuncEntidad.findAll", query="SELECT t FROM TicGruposFuncEntidad t")
public class TicGruposFuncEntidad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TicGruposFuncEntidadId ticGruposFuncEntidadId;

	@Column(name="ACTIVO_SN", length=1)
	private String activoSn;

	@Column(name="DESCRIPCION_ERROR", length=100)
	private String descripcionError;

	@Column(name="DESCRIPCION_ERROR_SISTEMA", length=100)
	private String descripcionErrorSistema;

	@Column(precision=2)
	private BigDecimal estado;

	@Column(name="ESTADO_SISTEMA", precision=2)
	private BigDecimal estadoSistema;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ESTADO")
	private Date fechaEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ESTADO_SISTEMA")
	private Date fechaEstadoSistema;

	@Column(length=8)
	private String fuente;

	@Column(precision=4)
	private BigDecimal revision;

	@Column(name="TIPO_FUENTE", length=3)
	private String tipoFuente;

	@Column(name="TIPO_GRUPO_FUNCIONAL", precision=2)
	private BigDecimal tipoGrupoFuncional;

	@Column(name="VERSION", precision=4)
	private BigDecimal version;

	public TicGruposFuncEntidad() {
	}

	
	public TicGruposFuncEntidadId getTicGruposFuncEntidadId() {
		return ticGruposFuncEntidadId;
	}

	public void setTicGruposFuncEntidadId(TicGruposFuncEntidadId ticGruposFuncEntidadId) {
		this.ticGruposFuncEntidadId = ticGruposFuncEntidadId;
	}

	public String getActivoSn() {
		return this.activoSn;
	}

	public void setActivoSn(String activoSn) {
		this.activoSn = activoSn;
	}

	public String getDescripcionError() {
		return this.descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getDescripcionErrorSistema() {
		return this.descripcionErrorSistema;
	}

	public void setDescripcionErrorSistema(String descripcionErrorSistema) {
		this.descripcionErrorSistema = descripcionErrorSistema;
	}

	public BigDecimal getEstado() {
		return this.estado;
	}

	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}

	public BigDecimal getEstadoSistema() {
		return this.estadoSistema;
	}

	public void setEstadoSistema(BigDecimal estadoSistema) {
		this.estadoSistema = estadoSistema;
	}

	public Date getFechaEstado() {
		return this.fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public Date getFechaEstadoSistema() {
		return this.fechaEstadoSistema;
	}

	public void setFechaEstadoSistema(Date fechaEstadoSistema) {
		this.fechaEstadoSistema = fechaEstadoSistema;
	}

	public String getFuente() {
		return this.fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
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

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}