package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the DES_GRUPO_FUNCIONAL database table.
 * 
 */
@Entity
@Table(name="DES_GRUPO_FUNCIONAL")
@NamedQuery(name="DesGrupoFuncional.findAll", query="SELECT d FROM DesGrupoFuncional d")
public class DesGrupoFuncional implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=10)
	private String aplicacion;

	@Column(name="CORRECCION_ERROR_SN", length=1)
	private String correccionErrorSn;

	@Column(length=100)
	private String descripcion;

	@Column(name="DOMINIO_AYUDAS", length=20)
	private String dominioAyudas;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_BAJA")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CKOUT")
	private Date fechaCkout;

	@Column(name="FORMATO_MENSAJE", precision=2)
	private BigDecimal formatoMensaje;

	@Column(name="GRUPO_DESARROLLO", length=20)
	private String grupoDesarrollo;

	@Column(precision=18)
	private BigDecimal iddescripcion;

	@Column(name="MODIFICABLE_SN", length=1)
	private String modificableSn;

	@Id
	@Column(name="NOMBRE_GRUPO_FUNCIONAL", length=32)
	private String nombreGrupoFuncional;

	@Column(length=10)
	private String tema;

	@Column(name="TIPO_GRUPO_FUNCIONAL", precision=2)
	private BigDecimal tipoGrupoFuncional;

	@Column(name="USUARIO_ALTA", precision=6)
	private BigDecimal usuarioAlta;

	@Column(name="USUARIO_BAJA", precision=6)
	private BigDecimal usuarioBaja;

	@Column(name="USUARIO_CKOUT", precision=6)
	private BigDecimal usuarioCkout;

	public DesGrupoFuncional() {
	}

	public String getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getCorreccionErrorSn() {
		return this.correccionErrorSn;
	}

	public void setCorreccionErrorSn(String correccionErrorSn) {
		this.correccionErrorSn = correccionErrorSn;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDominioAyudas() {
		return this.dominioAyudas;
	}

	public void setDominioAyudas(String dominioAyudas) {
		this.dominioAyudas = dominioAyudas;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaCkout() {
		return this.fechaCkout;
	}

	public void setFechaCkout(Date fechaCkout) {
		this.fechaCkout = fechaCkout;
	}

	public BigDecimal getFormatoMensaje() {
		return this.formatoMensaje;
	}

	public void setFormatoMensaje(BigDecimal formatoMensaje) {
		this.formatoMensaje = formatoMensaje;
	}

	public String getGrupoDesarrollo() {
		return this.grupoDesarrollo;
	}

	public void setGrupoDesarrollo(String grupoDesarrollo) {
		this.grupoDesarrollo = grupoDesarrollo;
	}

	public BigDecimal getIddescripcion() {
		return this.iddescripcion;
	}

	public void setIddescripcion(BigDecimal iddescripcion) {
		this.iddescripcion = iddescripcion;
	}

	public String getModificableSn() {
		return this.modificableSn;
	}

	public void setModificableSn(String modificableSn) {
		this.modificableSn = modificableSn;
	}

	public String getNombreGrupoFuncional() {
		return this.nombreGrupoFuncional;
	}

	public void setNombreGrupoFuncional(String nombreGrupoFuncional) {
		this.nombreGrupoFuncional = nombreGrupoFuncional;
	}

	public String getTema() {
		return this.tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public BigDecimal getTipoGrupoFuncional() {
		return this.tipoGrupoFuncional;
	}

	public void setTipoGrupoFuncional(BigDecimal tipoGrupoFuncional) {
		this.tipoGrupoFuncional = tipoGrupoFuncional;
	}

	public BigDecimal getUsuarioAlta() {
		return this.usuarioAlta;
	}

	public void setUsuarioAlta(BigDecimal usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public BigDecimal getUsuarioBaja() {
		return this.usuarioBaja;
	}

	public void setUsuarioBaja(BigDecimal usuarioBaja) {
		this.usuarioBaja = usuarioBaja;
	}

	public BigDecimal getUsuarioCkout() {
		return this.usuarioCkout;
	}

	public void setUsuarioCkout(BigDecimal usuarioCkout) {
		this.usuarioCkout = usuarioCkout;
	}

}