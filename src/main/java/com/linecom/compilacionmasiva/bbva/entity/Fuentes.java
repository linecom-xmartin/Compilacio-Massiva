package com.linecom.compilacionmasiva.bbva.entity;
//package com.compilacionmasiva.bbva.entity;
//
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
//
//
///**
// * The persistent class for FUENTES database table.
// * 
// */
///**
// * 
//ENTIDAD                   NOT NULL NUMBER(4)    
//FUENTE                    NOT NULL CHAR(8)      
//VERSION_01                NOT NULL NUMBER(1)    
//APLICACION                NOT NULL CHAR(10)     
//DESCRIPCION               NOT NULL VARCHAR2(80) 
//FECHA_INICIO              NOT NULL DATE         
//CODIGO_VIGENCIA_SN        NOT NULL CHAR(1)      
//TIPO_FUENTE               NOT NULL CHAR(3)      
//ESTADO_ULTIMA_COMPILACION NOT NULL CHAR(1)      
//NUMERO_EMPLEADO           NOT NULL NUMBER(6)    
//GRUPO_SERVIDOR            NOT NULL VARCHAR2(40) 
//REFERENCIA                NOT NULL CHAR(6)      
//TEMA                      NOT NULL CHAR(10)     
//
// * @author xmartin
// *
// */
//TODO: diferent schema
//@Entity
//@Table(name="FUENTES")
//@NamedQuery(name="Fuentes.findAll", query="SELECT f FROM FUENTES f")
public class Fuentes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=8)
	@Id
	private String fuente;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(precision=4)
	private BigDecimal entidad;
	
	@Column(name="VERSION_01", precision=1)
	private BigDecimal version01;
	
	@Column(length=8)
	private String aplicacion;
	
	@Column(length = 80)
	private String descripcion;
	
	@Column(name="CODIGO_VIGENCIA_SN", length=1)
	private String codigoVigenciaSn;
	
	@Column(name="TIPO_FUENTE", length=3)
	private String tipoFuente;
	
	@Column(name="ESTADO_ULTIMA_COMPILACION", length=1)
	private String estadoUltimaCompilacion;
	
	@Column(name="NUMERO_EMPLEADO", precision=6)
	private BigDecimal numeroEmpleado;
	
	@Column(name="GRUPO_SERVIDOR", length = 80)
	private String grupoServidor;
	
	@Column(length=6)
	private String referencia;
	
	@Column(length=10)
	private String tema;

	public Fuentes() {
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public BigDecimal getEntidad() {
		return entidad;
	}

	public void setEntidad(BigDecimal entidad) {
		this.entidad = entidad;
	}

	public BigDecimal getVersion01() {
		return version01;
	}

	public void setVersion01(BigDecimal version01) {
		this.version01 = version01;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoVigenciaSn() {
		return codigoVigenciaSn;
	}

	public void setCodigoVigenciaSn(String codigoVigenciaSn) {
		this.codigoVigenciaSn = codigoVigenciaSn;
	}

	public String getTipoFuente() {
		return tipoFuente;
	}

	public void setTipoFuente(String tipoFuente) {
		this.tipoFuente = tipoFuente;
	}

	public String getEstadoUltimaCompilacion() {
		return estadoUltimaCompilacion;
	}

	public void setEstadoUltimaCompilacion(String estadoUltimaCompilacion) {
		this.estadoUltimaCompilacion = estadoUltimaCompilacion;
	}

	public BigDecimal getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(BigDecimal numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	public String getGrupoServidor() {
		return grupoServidor;
	}

	public void setGrupoServidor(String grupoServidor) {
		this.grupoServidor = grupoServidor;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	
	
}