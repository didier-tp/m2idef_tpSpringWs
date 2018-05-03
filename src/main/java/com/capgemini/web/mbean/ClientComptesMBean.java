package com.capgemini.web.mbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.capgemini.entity.Compte;
import com.capgemini.service.IServiceCompte;

@ManagedBean
@SessionScoped
public class ClientComptesMBean {

	@ManagedProperty(value = "#{serviceCompteImpl}")
	private IServiceCompte serviceCompte; // ref vers service Spring (avec get/set)

	private Long numClient; // à saisir (h:inputText)
	private List<Compte> comptes; // à afficher dans tableau (h:dataTable)

	//<h:commandButton action="#{...loginClient}" />
	public String loginClient() {
		String suite = null;
		try {
			this.comptes = this.serviceCompte.rechercherComptesDuClient(numClient);
			suite = "comptes";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("echec login", e.getMessage()));
			//<h:messages globalOnly="true" />
		}
		return suite;
	}

	public IServiceCompte getServiceCompte() {
		return serviceCompte;
	}

	public void setServiceCompte(IServiceCompte serviceCompte) {
		this.serviceCompte = serviceCompte;
	}

	public Long getNumClient() {
		return numClient;
	}

	public void setNumClient(Long numClient) {
		this.numClient = numClient;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	// tous les get/set nécessaires et pas passionnant .

}
