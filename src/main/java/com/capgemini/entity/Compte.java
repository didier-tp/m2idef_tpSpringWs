package com.capgemini.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="compte")
@NamedQueries(
 @NamedQuery(name="Compte.findComptesDuClient" , 
            query="SELECT cpt FROM Compte cpt WHERE cpt.client.numero = ?1")
)
public class Compte {
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
		
	    private Long numero;
	    
	    @Column(length=32) //pour VARCHAR(32)
		private String label;
		private Double solde;
		//...
		
		@JsonIgnore
		@ManyToOne  //un ou plusieurs comptes pour un client
		@JoinColumn(name="refClient")//nom de la colonne clef étrangère (fk)
		private Client client; //avec get/set
		
		public Compte() {
		}
		
		public Compte(Long numero, String label, Double solde) {
			this.numero = numero;
			this.label = label;
			this.solde = solde;
		}
		
	
		


		@Override
		public String toString() {
			return "Compte [numero=" + numero + ", label=" + label + ", solde=" + solde + "]";
		}

		public Long getNumero() {
			return numero;
		}
		
		public void setNumero(Long numero) {
			this.numero = numero;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public Double getSolde() {
			return solde;
		}
		public void setSolde(Double solde) {
			this.solde = solde;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}
		
}
