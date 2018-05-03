package com.capgemini.service;

import java.util.List;

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
 *      * une logique métier (ex: virement = debit + credit)
 */
public interface IServiceCompte {
    public Compte rechercherCompteParNumero(Long numero);
    public List<Compte> rechercherComptesDuClient(Long numClient);
    public void transferer(double montant,Long numCptDeb,Long numCptCred);
    //...
    
}
