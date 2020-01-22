package com.linecom.compilacionmasiva.bbva.bean;

import java.math.BigDecimal;

public class VistaTicCompilacionesMasiva {
	
	private String nombreGrupoFuncional;
	private BigDecimal entidad;
	private String entorno;
	private BigDecimal resultadoCompilacion;
	private byte[] fichero;
	private String tipoFuente;
	
	public VistaTicCompilacionesMasiva(String nombreGrupoFuncional, BigDecimal entidad, String entorno,
			BigDecimal resultadoCompilacion, byte[] fichero, String tipoFuente) {
		super();
		this.nombreGrupoFuncional = nombreGrupoFuncional;
		this.entidad = entidad;
		this.entorno = entorno;
		this.resultadoCompilacion = resultadoCompilacion;
		this.fichero = fichero;
		this.tipoFuente = tipoFuente;
	}

	public String getNombreGrupoFuncional() {
		return nombreGrupoFuncional;
	}

	public void setNombreGrupoFuncional(String nombreGrupoFuncional) {
		this.nombreGrupoFuncional = nombreGrupoFuncional;
	}

	public BigDecimal getEntidad() {
		return entidad;
	}

	public void setEntidad(BigDecimal entidad) {
		this.entidad = entidad;
	}

	public String getEntorno() {
		return entorno;
	}

	public void setEntorno(String entorno) {
		this.entorno = entorno;
	}

	public BigDecimal getResultadoCompilacion() {
		return resultadoCompilacion;
	}

	public void setResultadoCompilacion(BigDecimal resultadoCompilacion) {
		this.resultadoCompilacion = resultadoCompilacion;
	}

	public byte[] getFichero() {
		return fichero;
	}

	public void setFichero(byte[] fichero) {
		this.fichero = fichero;
	}

	public String getTipoFuente() {
		return tipoFuente;
	}

	public void setTipoFuente(String tipoFuente) {
		this.tipoFuente = tipoFuente;
	}
	
	
}
