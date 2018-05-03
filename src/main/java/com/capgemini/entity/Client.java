package com.capgemini.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
		
	    private Long numero;
	    
	    @Column(length=32) //pour VARCHAR(32)
		private String nom;
	    
	    @Column(length=32) //pour VARCHAR(32)
		private String prenom;
	    
	    @Column(length=64) //pour VARCHAR(64)
	    private String adresse;
		//...
	    
	    @OneToMany(mappedBy="client",fetch=FetchType.LAZY) //un client peut avoir un ou plusieurs comptes
	    //mapped="nom_java_de_la_relation_many_to_one_inverse"
	    private List<Compte> comptes;//avec get/set
		
		public Client() {
		}
		
		




		@Override
		public String toString() {
			return "Client [numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + "]";
		}






		public Long getNumero() {
			return numero;
		}

		public void setNumero(Long numero) {
			this.numero = numero;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}



		public List<Compte> getComptes() {
			return comptes;
		}



		public void setComptes(List<Compte> comptes) {
			this.comptes = comptes;
		}
	
		
		
	
		
}
