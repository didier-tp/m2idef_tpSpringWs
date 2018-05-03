package com.capgemini.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.entity.Client;

/*
 * DAO = Data Access Object :
 * objet spécialisé dans l'acces aux données
 * (alias DataService ou Repository ou ...)
 * avec methodes "CRUD" (avec souvent du SQL)
 * 
 * avec throws RuntimeException implicites
 * 
 * JpaRepository herite de CrudRepository (encore plus abstrait)
 */

//public interface IDaoClient extends JpaRepository<Client,Long>{
public interface IDaoClient extends CrudRepository<Client,Long>{
   /* méthodes héritées:
  	   ... findOne(...)
  	   .... findAll()
  	   ...save(...)
  	   ...delete(...)
    */

}
