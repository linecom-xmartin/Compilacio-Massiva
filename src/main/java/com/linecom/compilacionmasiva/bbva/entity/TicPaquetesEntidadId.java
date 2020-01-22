package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class TicPaquetesEntidadId implements Serializable {

	private static final long serialVersionUID = 6559731700570424055L;

	@NotNull
	@Column(precision=10)
	private BigDecimal paquete;
	
	@NotNull
	@Column(precision=4)
	private BigDecimal entidad;

	
	public TicPaquetesEntidadId() {
		super();
	}

	public TicPaquetesEntidadId(@NotNull BigDecimal paquete, @NotNull BigDecimal entidad) {
		super();
		this.paquete = paquete;
		this.entidad = entidad;
	}

	public BigDecimal getPaquete() {
		return paquete;
	}

	public void setPaquete(BigDecimal paquete) {
		this.paquete = paquete;
	}

	public BigDecimal getEntidad() {
		return entidad;
	}

	public void setEntidad(BigDecimal entidad) {
		this.entidad = entidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
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
		TicPaquetesEntidadId other = (TicPaquetesEntidadId) obj;
		if (entidad == null) {
			if (other.entidad != null)
				return false;
		} else if (!entidad.equals(other.entidad))
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
		return "TicPaquetesEntidadId [paquete=" + paquete + ", entidad=" + entidad + "]";
	}

	

}
