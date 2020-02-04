package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TIC_FUENTES_ENTORNOS database table.
 * 
 */
@Entity
@Table(name="TIC_FUENTES_ENTORNOS")
@NamedQuery(name="TicFuentesEntornos.findAll", query="SELECT t FROM TicFuentesEntornos t")
public class TicFuentesEntornos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TicFuentesEntornosId ticFuentesEntornosId;

	@Column(name="ESTADO_COMPILACION", length=1)
	private String estadoCompilacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_COMPILACION")
	private Date fechaCompilacion;

	@Column(length=10)
	private String esquema;

	@Lob
	@Column(name="FUENTE_CBL")
	private byte[] fuenteCbl;

	@Column(name="OPCIONES_COMPILACION", precision=2)
	private BigDecimal opcionesCompilacion;
	
	@Column(name="XA_ASN", length=1)
	private String XaAsn;
	
	@Column(length=8)
	private String fuente;

	@Column(name="TIPO_FUENTE", length=3)
	private String tipoFuente;

	@Column(name="GENERICO_SN", length=1)
	private String genericoSn;
	
	@Column(name="TRASPASABLE_SN", length=1)
	private String traspasableSn;

	@Column(name="VERSION_COMPILACION", precision=4)
	private BigDecimal versionCompilacion;
	
	@Column(name="REVISION_COMPILACION", precision=4)
	private BigDecimal revisionCompilacion;

	public TicFuentesEntornos() {
	}

	public TicFuentesEntornosId getTicFuentesEntornosId() {
		return ticFuentesEntornosId;
	}

	public void setTicFuentesEntornosId(TicFuentesEntornosId ticFuentesEntornosId) {
		this.ticFuentesEntornosId = ticFuentesEntornosId;
	}

	public String getEstadoCompilacion() {
		return estadoCompilacion;
	}

	public void setEstadoCompilacion(String estadoCompilacion) {
		this.estadoCompilacion = estadoCompilacion;
	}

	public Date getFechaCompilacion() {
		return fechaCompilacion;
	}

	public void setFechaCompilacion(Date fechaCompilacion) {
		this.fechaCompilacion = fechaCompilacion;
	}

	public String getEsquema() {
		return esquema;
	}

	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}

	public byte[] getFuenteCbl() {
		return fuenteCbl;
	}

	public void setFuenteCbl(byte[] fuenteCbl) {
		this.fuenteCbl = fuenteCbl;
	}

	public BigDecimal getOpcionesCompilacion() {
		return opcionesCompilacion;
	}

	public void setOpcionesCompilacion(BigDecimal opcionesCompilacion) {
		this.opcionesCompilacion = opcionesCompilacion;
	}

	public String getXaAsn() {
		return XaAsn;
	}

	public void setXaAsn(String xaAsn) {
		XaAsn = xaAsn;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getTipoFuente() {
		return tipoFuente;
	}

	public void setTipoFuente(String tipoFuente) {
		this.tipoFuente = tipoFuente;
	}

	public String getGenericoSn() {
		return genericoSn;
	}

	public void setGenericoSn(String genericoSn) {
		this.genericoSn = genericoSn;
	}

	public String getTraspasableSn() {
		return traspasableSn;
	}

	public void setTraspasableSn(String traspasableSn) {
		this.traspasableSn = traspasableSn;
	}

	public BigDecimal getVersionCompilacion() {
		return versionCompilacion;
	}

	public void setVersionCompilacion(BigDecimal versionCompilacion) {
		this.versionCompilacion = versionCompilacion;
	}

	public BigDecimal getRevisionCompilacion() {
		return revisionCompilacion;
	}

	public void setRevisionCompilacion(BigDecimal revisionCompilacion) {
		this.revisionCompilacion = revisionCompilacion;
	}
}