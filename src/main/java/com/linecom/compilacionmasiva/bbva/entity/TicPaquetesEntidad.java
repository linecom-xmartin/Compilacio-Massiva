package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TIC_PAQUETES_ENTIDAD database table.
 * 
 */
@Entity
@Table(name="TIC_PAQUETES_ENTIDAD")
@NamedQuery(name="TicPaquetesEntidad.findAll", query="SELECT t FROM TicPaquetesEntidad t")
public class TicPaquetesEntidad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TicPaquetesEntidadId ticPaquetesEntidadId;

	@Column(name="COMP_FULL_CHEK_SN", length=1)
	private String compFullChekSn;

	@Column(name="ESPECIAL_SN", length=1)
	private String especialSn;

	@Column(precision=2)
	private BigDecimal estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ESTADO")
	private Date fechaEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_SOLICITUD")
	private Date fechaSolicitud;

	@Column(name="PUBLICO_SN", length=1)
	private String publicoSn;

	@Column(name="SIMPLE_SN", length=1)
	private String simpleSn;

	@Column(precision=6)
	private BigDecimal usuario;

	public TicPaquetesEntidad() {
	}
	
	
	public TicPaquetesEntidadId getTicPaquetesEntidadId() {
		return ticPaquetesEntidadId;
	}

	public void setTicPaquetesEntidadId(TicPaquetesEntidadId ticPaquetesEntidadId) {
		this.ticPaquetesEntidadId = ticPaquetesEntidadId;
	}

	public String getCompFullChekSn() {
		return this.compFullChekSn;
	}

	public void setCompFullChekSn(String compFullChekSn) {
		this.compFullChekSn = compFullChekSn;
	}

	public String getEspecialSn() {
		return this.especialSn;
	}

	public void setEspecialSn(String especialSn) {
		this.especialSn = especialSn;
	}

	public BigDecimal getEstado() {
		return this.estado;
	}

	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}

	public Date getFechaEstado() {
		return this.fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getPublicoSn() {
		return this.publicoSn;
	}

	public void setPublicoSn(String publicoSn) {
		this.publicoSn = publicoSn;
	}

	public String getSimpleSn() {
		return this.simpleSn;
	}

	public void setSimpleSn(String simpleSn) {
		this.simpleSn = simpleSn;
	}

	public BigDecimal getUsuario() {
		return this.usuario;
	}

	public void setUsuario(BigDecimal usuario) {
		this.usuario = usuario;
	}

}