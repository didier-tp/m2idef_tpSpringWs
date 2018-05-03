package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Client;
import com.capgemini.entity.Compte;

/*
 * Service métier "ServiceCompte"
 * alias "business service"
 * 
 * qui délègue au DAO la persistance (les méthodes "CRUD")
 * et qui gère :
 *      * les transactions (via l'aide de spring)
 *      * les règles de gestion (verifier conditions avant de faire 
 *                               n'importe quoi).
 *      * une logique métier 
 */
public interface IServiceClient {
    public Client rechercherClientParNumero(Long numero);
    public List<Client> rechercherTousClient();
    public Client creerClient(Client c);//return Client with auto_incr pk
    public void modifierClient(Client c);
    public void supprimerClient(long numclient);
    
}
