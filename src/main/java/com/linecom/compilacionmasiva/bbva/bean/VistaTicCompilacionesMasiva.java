package com.linecom.compilacionmasiva.bbva.bean;

import java.math.BigDecimal;

public class VistaTicCompilacionesMasiva {
	
	private String nombreGrupoFuncional;
	private String entorno;
	private String fuente;
	private BigDecimal resultadoCompilacion;
	private byte[] ficheroFuente;
	private byte[] ficheroResultado;
	private String tipoFuente;
	
	public VistaTicCompilacionesMasiva(String nombreGrupoFuncional, String entorno, String fuente,
			BigDecimal resultadoCompilacion, byte[] ficheroFuente, byte[] ficheroResultado, String tipoFuente) {
		super();
		this.nombreGrupoFuncional = nombreGrupoFuncional;
		this.entorno = entorno;
		this.fuente = fuente;
		this.resultadoCompilacion = resultadoCompilacion;
		this.ficheroFuente = ficheroFuente;
		this.ficheroResultado = ficheroResultado;
		this.tipoFuente = tipoFuente;
	}

	public String getNombreGrupoFuncional() {
		return nombreGrupoFuncional;
	}

	public void setNombreGrupoFuncional(String nombreGrupoFuncional) {
		this.nombreGrupoFuncional = nombreGrupoFuncional;
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

	public byte[] getFicheroFuente() {
		return ficheroFuente;
	}

	public void setFicheroFuente(byte[] ficheroFuente) {
		this.ficheroFuente = ficheroFuente;
	}

	public byte[] getFicheroResultado() {
		return ficheroResultado;
	}

	public void setFicheroResultado(byte[] ficheroResultado) {
		this.ficheroResultado = ficheroResultado;
	}
	
	public String getTipoFuente() {
		return tipoFuente;
	}

	public void setTipoFuente(String tipoFuente) {
		this.tipoFuente = tipoFuente;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	
}
