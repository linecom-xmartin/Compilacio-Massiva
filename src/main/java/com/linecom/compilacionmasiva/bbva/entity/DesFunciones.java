package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the DES_FUNCIONES database table.
 * 
 */
@Entity
@Table(name="DES_FUNCIONES")
@NamedQuery(name="DesFunciones.findAll", query="SELECT d FROM DesFunciones d")
public class DesFunciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NOMBRE_GRUPO_FUNCIONAL", length=32)
	private String nombreGrupoFuncional;

	@Column(length=20)
	private String funcion;
	
	@Column(name="GRUPO_FUNCIONAL_FUENTE", length=100)
	private String grupoFuncionalFuente;

	@Column(length=25)
	private String fuente;
	
	private BigDecimal identificador;

	
	public DesFunciones() {
	}


	public String getNombreGrupoFuncional() {
		return nombreGrupoFuncional;
	}


	public void setNombreGrupoFuncional(String nombreGrupoFuncional) {
		this.nombreGrupoFuncional = nombreGrupoFuncional;
	}


	public String getFuncion() {
		return funcion;
	}


	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}


	public String getGrupoFuncionalFuente() {
		return grupoFuncionalFuente;
	}


	public void setGrupoFuncionalFuente(String grupoFuncionalFuente) {
		this.grupoFuncionalFuente = grupoFuncionalFuente;
	}


	public String getFuente() {
		return fuente;
	}


	public void setFuente(String fuente) {
		this.fuente = fuente;
	}


	public BigDecimal getIdentificador() {
		return identificador;
	}


	public void setIdentificador(BigDecimal identificador) {
		this.identificador = identificador;
	}

	
	

}