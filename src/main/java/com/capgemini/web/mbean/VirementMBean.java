package com.capgemini.web.mbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.capgemini.service.IServiceCompte;

@ManagedBean
@RequestScoped
public class VirementMBean {
	
	@ManagedProperty(value = "#{serviceCompteImpl}")
	private IServiceCompte serviceCompte; // ref vers service Spring (avec get/set)
	
	@ManagedProperty(value = "#{clientComptesMBean}") //en SessionScoped
	private ClientComptesMBean clientComptesMBean; // ref vers autre MBean (avec get/set)
	
	private Long numCptDeb; //à saisir ou selectionner (get/set)
	private Long numCptCred; //à saisir ou selectionner (get/set)
	private Double montant; //à saisir (get/set)
	
	//<h:commandButton action="#{...virement}" />
		public String virement() {
			String suite = null;
			try {
				//effectuer le virement en base via Service et dao spring:
				this.serviceCompte.transferer(montant, numCptDeb, numCptCred);
				//réactualiser les valeurs en mémoire (avant ré-affichage):
				clientComptesMBean.setComptes(
						this.serviceCompte.rechercherComptesDuClient(
								        clientComptesMBean.getNumClient())); 
				suite = "comptes"; //pour afficher les nouvelles valeurs
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("echec virement", e.getMessage()));
				}
			return suite;
		}

		public IServiceCompte getServiceCompte() {
			return serviceCompte;
		}

		public void setServiceCompte(IServiceCompte serviceCompte) {
			this.serviceCompte = serviceCompte;
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

		public Double getMontant() {
			return montant;
		}

		public void setMontant(Double montant) {
			this.montant = montant;
		}

		public ClientComptesMBean getClientComptesMBean() {
			return clientComptesMBean;
		}

		public void setClientComptesMBean(ClientComptesMBean clientComptesMBean) {
			this.clientComptesMBean = clientComptesMBean;
		}

		
		
}
