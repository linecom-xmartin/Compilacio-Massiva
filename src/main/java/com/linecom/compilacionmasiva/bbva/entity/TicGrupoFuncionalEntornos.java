package com.linecom.compilacionmasiva.bbva.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TIC_GRUPOS_FUNC_ENTIDAD database table.
 * 
 */
@Entity
@Table(name="TIC_GRUPO_FUNCIONAL_ENTORNOS")
@NamedQuery(name="TicGrupoFuncionalEntornos.findAll", query="SELECT t FROM TicGrupoFuncionalEntornos t")
public class TicGrupoFuncionalEntornos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TicGruposFuncEntidadId ticGruposFuncEntidadId;

	@Column(name="ACTIVO_SN", length=1)
	private String activoSn;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_VERSION")
	private Date fechaVersion;

	@Column(precision=4)
	private BigDecimal revision;

	@Column(name="TIPO_FUENTE", length=3)
	private String tipoFuente;

	@Column(name="TIPO_GRUPO_FUNCIONAL", precision=2)
	private BigDecimal tipoGrupoFuncional;

	@Column(name="VERSION", precision=4)
	private BigDecimal version;
	
	@Column(length=50)
	private String entorno;

	public TicGrupoFuncionalEntornos() {
	}

	
	public TicGruposFuncEntidadId getTicGruposFuncEntidadId() {
		return ticGruposFuncEntidadId;
	}

	public void setTicGruposFuncEntidadId(TicGruposFuncEntidadId ticGruposFuncEntidadId) {
		this.ticGruposFuncEntidadId = ticGruposFuncEntidadId;
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

	public String getTipoFuente() {
		return this.tipoFuente;
	}

	public void setTipoFuente(String tipoFuente) {
		this.tipoFuente = tipoFuente;
	}

	public BigDecimal getTipoGrupoFuncional() {
		return this.tipoGrupoFuncional;
	}

	public void setTipoGrupoFuncional(BigDecimal tipoGrupoFuncional) {
		this.tipoGrupoFuncional = tipoGrupoFuncional;
	}

	public BigDecimal getVersion() {
		return this.version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}


	public Date getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	public Date getFechaVersion() {
		return fechaVersion;
	}


	public void setFechaVersion(Date fechaVersion) {
		this.fechaVersion = fechaVersion;
	}


	public String getEntorno() {
		return entorno;
	}


	public void setEntorno(String entorno) {
		this.entorno = entorno;
	}
	
	

}