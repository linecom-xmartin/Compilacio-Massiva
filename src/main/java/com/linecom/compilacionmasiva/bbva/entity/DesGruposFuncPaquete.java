package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DES_GRUPOS_FUNC_PAQUETE database table.
 * 
 */
@Entity
@Table(name="DES_GRUPOS_FUNC_PAQUETE")
@NamedQuery(name="DesGruposFuncPaquete.findAll", query="SELECT d FROM DesGruposFuncPaquete d")
public class DesGruposFuncPaquete implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTIVO_SN", length=1)
	private String activoSn;
	
	@EmbeddedId
	private DesGruposFuncPaqueteId desGruposFuncPaqueteId;

	@Column(precision=4)
	private BigDecimal revision;

	@Column(precision=6)
	private BigDecimal usuario;

	@Column(name="VERSION", precision=4)
	private BigDecimal version;

	public DesGruposFuncPaquete() {
	}

	public DesGruposFuncPaqueteId getDesGruposFuncPaqueteId() {
		return desGruposFuncPaqueteId;
	}


	public void setDesGruposFuncPaqueteId(DesGruposFuncPaqueteId desGruposFuncPaqueteId) {
		this.desGruposFuncPaqueteId = desGruposFuncPaqueteId;
	}


	public String getActivoSn() {
		return this.activoSn;
	}

	public void setActivoSn(String activoSn) {
		this.activoSn = activoSn;
	}

	public BigDecimal getRevision() {
		return this.revision;
	}

	public void setRevision(BigDecimal revision) {
		this.revision = revision;
	}

	public BigDecimal getUsuario() {
		return this.usuario;
	}

	public void setUsuario(BigDecimal usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}