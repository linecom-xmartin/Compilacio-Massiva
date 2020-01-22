package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the DES_VERSIONES_GF database table.
 * 
 */
@Entity
@Table(name="DES_VERSIONES_GF")
@NamedQuery(name="DesVersionesGF.findAll", query="SELECT d FROM DesVersionesGF d")
public class DesVersionesGF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NOMBRE_GRUPO_FUNCIONAL", length=32)
	private String nombreGrupoFuncional;

	@Column(precision=4)
	private BigDecimal revision;

	@Column(length=1)
	private String estado;

	@Column(name="VERSION", precision=4)
	private BigDecimal version;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ESTADO")
	private Date fechaEstado;
	
	@Column(length=255)
	private String comentario;
	
	public DesVersionesGF() {
	}


	public String getNombreGrupoFuncional() {
		return nombreGrupoFuncional;
	}


	public void setNombreGrupoFuncional(String nombreGrupoFuncional) {
		this.nombreGrupoFuncional = nombreGrupoFuncional;
	}


	public BigDecimal getRevision() {
		return revision;
	}


	public void setRevision(BigDecimal revision) {
		this.revision = revision;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public BigDecimal getVersion() {
		return version;
	}


	public void setVersion(BigDecimal version) {
		this.version = version;
	}


	public Date getFechaEstado() {
		return fechaEstado;
	}


	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	

}