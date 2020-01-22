package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the DES_PAQUETES database table.
 * 
 */
@Entity
@Table(name="DES_PAQUETES")
@NamedQuery(name="DesPaquete.findAll", query="SELECT d FROM DesPaquete d")
public class DesPaquete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COMP_FULL_CHEK_SN", length=1)
	private String compFullChekSn;

	@Column(length=100)
	private String descripcion;

	@Column(precision=4)
	private BigDecimal entidad;

	@Column(name="ESPECIAL_SN", length=1)
	private String especialSn;

	@Column(name="ESTADO_PAQUETE", precision=2)
	private BigDecimal estadoPaquete;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ESTADO")
	private Date fechaEstado;

	@Lob
	@Column(name="FICHERO_INSTALACION")
	private byte[] ficheroInstalacion;

	@Column(name="GRUPO_DESARROLLO", length=20)
	private String grupoDesarrollo;

	@Column(name="NOMBRE_PAQUETE", length=30)
	private String nombrePaquete;

	@Id
	@GeneratedValue(generator="seqGen")
	@SequenceGenerator(name="seqGen",sequenceName="SEQ_PAQUETES", allocationSize=1)
	@Column(precision=10)
	private BigDecimal paquete;

	@Column(name="PUBLICO_SN", length=1)
	private String publicoSn;

	@Column(name="SIMPLE_SN", length=1)
	private String simpleSn;

	@Column(name="TIPO_FICHERO_INSTALACION", length=3)
	private String tipoFicheroInstalacion;

	@Column(precision=6)
	private BigDecimal usuario;

	public DesPaquete() {
	}

	public String getCompFullChekSn() {
		return this.compFullChekSn;
	}

	public void setCompFullChekSn(String compFullChekSn) {
		this.compFullChekSn = compFullChekSn;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getEntidad() {
		return this.entidad;
	}

	public void setEntidad(BigDecimal entidad) {
		this.entidad = entidad;
	}

	public String getEspecialSn() {
		return this.especialSn;
	}

	public void setEspecialSn(String especialSn) {
		this.especialSn = especialSn;
	}

	public BigDecimal getEstadoPaquete() {
		return this.estadoPaquete;
	}

	public void setEstadoPaquete(BigDecimal estadoPaquete) {
		this.estadoPaquete = estadoPaquete;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaEstado() {
		return this.fechaEstado;
	}

	public void setFechaEstado(Date fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	public byte[] getFicheroInstalacion() {
		return this.ficheroInstalacion;
	}

	public void setFicheroInstalacion(byte[] ficheroInstalacion) {
		this.ficheroInstalacion = ficheroInstalacion;
	}

	public String getGrupoDesarrollo() {
		return this.grupoDesarrollo;
	}

	public void setGrupoDesarrollo(String grupoDesarrollo) {
		this.grupoDesarrollo = grupoDesarrollo;
	}

	public String getNombrePaquete() {
		return this.nombrePaquete;
	}

	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}

	public BigDecimal getPaquete() {
		return this.paquete;
	}

	public void setPaquete(BigDecimal paquete) {
		this.paquete = paquete;
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

	public String getTipoFicheroInstalacion() {
		return this.tipoFicheroInstalacion;
	}

	public void setTipoFicheroInstalacion(String tipoFicheroInstalacion) {
		this.tipoFicheroInstalacion = tipoFicheroInstalacion;
	}

	public BigDecimal getUsuario() {
		return this.usuario;
	}

	public void setUsuario(BigDecimal usuario) {
		this.usuario = usuario;
	}

}