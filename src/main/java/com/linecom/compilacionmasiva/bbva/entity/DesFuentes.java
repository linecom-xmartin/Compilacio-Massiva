package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DES_FUNCIONES database table.
 * 
 */
@Entity
@Table(name="DES_FUENTES")
@NamedQuery(name="DesFuentes.findAll", query="SELECT d FROM DesFuentes d")
public class DesFuentes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NOMBRE_GRUPO_FUNCIONAL", length=32)
	private String nombreGrupoFuncional;

	@Column(name="GENERICO_SN", length=8)
	private String genericoSN;

	@Column(length=25)
	private String fuente;
	
	@Column(name="TIPO_FUENTE", length=25)
	private String tipoFuente;

	
	public DesFuentes() {
	}


	public String getNombreGrupoFuncional() {
		return nombreGrupoFuncional;
	}


	public void setNombreGrupoFuncional(String nombreGrupoFuncional) {
		this.nombreGrupoFuncional = nombreGrupoFuncional;
	}


	public String getFuente() {
		return fuente;
	}


	public void setFuente(String fuente) {
		this.fuente = fuente;
	}


	public String getGenericoSN() {
		return genericoSN;
	}


	public void setGenericoSN(String genericoSN) {
		this.genericoSN = genericoSN;
	}


	public String getTipoFuente() {
		return tipoFuente;
	}


	public void setTipoFuente(String tipoFuente) {
		this.tipoFuente = tipoFuente;
	}
	
	

}