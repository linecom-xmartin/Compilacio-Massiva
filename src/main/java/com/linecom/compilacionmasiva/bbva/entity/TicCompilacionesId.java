package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class TicCompilacionesId implements Serializable {

	private static final long serialVersionUID = 668618992410306716L;

	@NotNull
	private BigDecimal entidad;
	
	@NotNull
	private String entorno;
	
	@NotNull
	@Column(name="NOMBRE_GRUPO_FUNCIONAL")
	private String nombreGrupoFuncional;

	
	public TicCompilacionesId() {
		super();
	}

	public TicCompilacionesId(@NotNull BigDecimal entidad, @NotNull String entorno,
			@NotNull String nombreGrupoFuncional) {
		super();
		this.entidad = entidad;
		this.entorno = entorno;
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

	public String getNombreGrupoFuncional() {
		return nombreGrupoFuncional;
	}

	public void setNombreGrupoFuncional(String nombreGrupoFuncional) {
		this.nombreGrupoFuncional = nombreGrupoFuncional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
		result = prime * result + ((entorno == null) ? 0 : entorno.hashCode());
		result = prime * result + ((nombreGrupoFuncional == null) ? 0 : nombreGrupoFuncional.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicCompilacionesId other = (TicCompilacionesId) obj;
		if (entidad == null) {
			if (other.entidad != null)
				return false;
		} else if (!entidad.equals(other.entidad))
			return false;
		if (entorno == null) {
			if (other.entorno != null)
				return false;
		} else if (!entorno.equals(other.entorno))
			return false;
		if (nombreGrupoFuncional == null) {
			if (other.nombreGrupoFuncional != null)
				return false;
		} else if (!nombreGrupoFuncional.equals(other.nombreGrupoFuncional))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TicCompilacionesMasivaId [entidad=" + entidad + ", entorno=" + entorno + ", nombreGrupoFuncional="
				+ nombreGrupoFuncional + "]";
	}
	
}
