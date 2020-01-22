package com.linecom.compilacionmasiva.bbva.bean;

public class ResumTicCompilacionesMasiva {
	
	private long numResultats;
	private String descripcioResultat;
	
	public ResumTicCompilacionesMasiva(long numResultats, String descripcioResultat) {
		super();
		this.numResultats = numResultats;
		this.descripcioResultat = descripcioResultat;
	}
	public long getNumResultats() {
		return numResultats;
	}
	public void setNumResultats(long numResultats) {
		this.numResultats = numResultats;
	}
	public String getDescripcioResultat() {
		return descripcioResultat;
	}
	public void setDescripcioResultat(String descripcioResultat) {
		this.descripcioResultat = descripcioResultat;
	}
}
