package com.capgemini.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.entity.Compte;

/*
 * DAO = Data Access Object :
 * objet spécialisé dans l'acces aux données
 * (alias DataService ou Repository ou ...)
 * avec methodes "CRUD" (avec souvent du SQL)
 * 
 * avec throws RuntimeException implicites
 */

//public interface IDaoCompte extends JpaRepository<Compte,Long>{
public interface IDaoCompte extends CrudRepository<Compte,Long>{
 /* méthodes héritées:
	   ... findOne(...)
	   .... findAll()
	   ...save(...)
	   ...delete(...)
  */
	//NB le code d'une méthode de recherche simple findByLabel() 
	//peut être généré automatiquement
	public List<Compte> findByLabel(String label);
	
	//NB: le code d'une méthode de recherche complexe peut (en JPA)
	//être codé comme un @NameQuery de nom "Compte.findComptesDuClient"
	public List<Compte> findComptesDuClient(Long numClient);
}


