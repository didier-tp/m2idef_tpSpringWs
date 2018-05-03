package com.capgemini.dao;

import java.util.List;

import com.capgemini.entity.Compte;

/*
 * DAO = Data Access Object :
 * objet spécialisé dans l'acces aux données
 * (alias DataService ou Repository ou ...)
 * avec methodes "CRUD" (avec souvent du SQL)
 * 
 * avec throws RuntimeException implicites
 */
public interface IDaoCompte {
   public Compte findCompteByNum(Long numero);
   public List<Compte> findComptesDuClient(Long numClient);
   public Compte createCompte(Compte c);//return compte with auto_incr pk
   public void updateCompte(Compte c);
   public void deleteCompte(Compte c);
   //...
}
