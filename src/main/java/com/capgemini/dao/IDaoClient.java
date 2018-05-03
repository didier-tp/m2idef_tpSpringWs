package com.capgemini.dao;

import java.util.List;

import com.capgemini.entity.Client;

/*
 * DAO = Data Access Object :
 * objet spécialisé dans l'acces aux données
 * (alias DataService ou Repository ou ...)
 * avec methodes "CRUD" (avec souvent du SQL)
 * 
 * avec throws RuntimeException implicites
 */
public interface IDaoClient {
   public Client findClientByNum(Long numero);
   public List<Client> findAllClients();
   public Client createClient(Client c);//return Client with auto_incr pk
   public void updateClient(Client c);
   public void deleteClient(Client c);
   //...
}
