package com.capgemini.web.rest.data;

public class OrdreVirement {
	private Double montant;
	private Long numCptDeb;
	private Long numCptCred;
	
	private Boolean ok;
	private String message;
	
	public OrdreVirement() {
	}
	
	
	
	@Override
	public String toString() {
		return "OrdreVirement [montant=" + montant + ", numCptDeb=" + numCptDeb + ", numCptCred=" + numCptCred + ", ok="
				+ ok + ", message=" + message + "]";
	}



	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public Long getNumCptDeb() {
		return numCptDeb;
	}
	public void setNumCptDeb(Long numCptDeb) {
		this.numCptDeb = numCptDeb;
	}
	public Long getNumCptCred() {
		return numCptCred;
	}
	public void setNumCptCred(Long numCptCred) {
		this.numCptCred = numCptCred;
	}
	public Boolean getOk() {
		return ok;
	}
	public void setOk(Boolean ok) {
		this.ok = ok;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
