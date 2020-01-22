package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class DesGruposFuncPaqueteId implements Serializable {

	private static final long serialVersionUID = 6559731700570424055L;

	@NotNull
	@Column(precision=10)
	private BigDecimal paquete;
	
	@NotNull
	@Column(name="NOMBRE_GRUPO_FUNCIONAL", length=32)
	private String nombreGrupoFuncional;

	
	public DesGruposFuncPaqueteId() {
		super();
	}

	public DesGruposFuncPaqueteId(@NotNull BigDecimal paquete, @NotNull String nombreGrupoFuncional) {
		super();
		this.paquete = paquete;
		this.nombreGrupoFuncional = nombreGrupoFuncional;
	}

	public BigDecimal getPaquete() {
		return paquete;
	}

	public void setPaquete(BigDecimal paquete) {
		this.paquete = paquete;
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
		result = prime * result + ((nombreGrupoFuncional == null) ? 0 : nombreGrupoFuncional.hashCode());
		result = prime * result + ((paquete == null) ? 0 : paquete.hashCode());
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
		DesGruposFuncPaqueteId other = (DesGruposFuncPaqueteId) obj;
		if (nombreGrupoFuncional == null) {
			if (other.nombreGrupoFuncional != null)
				return false;
		} else if (!nombreGrupoFuncional.equals(other.nombreGrupoFuncional))
			return false;
		if (paquete == null) {
			if (other.paquete != null)
				return false;
		} else if (!paquete.equals(other.paquete))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DesGruposFuncPaqueteId [paquete=" + paquete + ", nombreGrupoFuncional=" + nombreGrupoFuncional + "]";
	}

}
