package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.IDaoCompte;
import com.capgemini.entity.Compte;

/*
 * classe d'implémentation du service
 * qui ne fonctionne qu'avec un composant dao en arrière plan
 * ce dao est une "dépendance" à injecter et à utiliser pour déléguer
 */
//@Component
@Service //@Service herite de @Component
@Transactional(/* propagation=Propagation.REQUIRED par defaut */)
public class ServiceCompteImpl implements IServiceCompte {
	//IDaoCompte est un type abstrait qui en englobe DoaCompteJpa et DaoCompteSimu
	private IDaoCompte daoCompte; //null par défaut 
	
    //méthode d'injection de dépendance qui sera automatiquement appelée par Spring
	@Autowired 
	//injection automatique d'un composant spring existant compatible avec interface
	public void setDaoCompte(IDaoCompte daoCompte) {
		this.daoCompte = daoCompte;
	}

	@Override
	public Compte rechercherCompteParNumero(Long numero) {
		return daoCompte.findOne(numero);
	}

	@Override
	public List<Compte> rechercherComptesDuClient(Long numClient) {
		return daoCompte.findComptesDuClient(numClient);
	}

	@Override
	//@Transactional ici ou au dessus de la classe
	public void transferer(double montant, Long numCptDeb, Long numCptCred) {
		try {
			Compte cptDeb = daoCompte.findOne(numCptDeb);
			cptDeb.setSolde(cptDeb.getSolde()-montant);
			//daoCompte.updateCompte(cptDeb);//automatique avec @Transactional
			
			Compte cptCred = daoCompte.findOne(numCptCred);
			cptCred.setSolde(cptCred.getSolde()+montant);
			//daoCompte.updateCompte(cptCred);//necessaire que si pas de @Transactional
		} catch (Exception e) {
			//on génère des lignes de logs avec une api de log (ex: log4j ou slf4j)
			//ET
			throw new RuntimeException("echec transfert",e);
			//ou bien throw new ClassePersonnaliseeHeritantDeRuntimeException()
		}
		
		//si @Transactional au dessus de cette méthode ou bien au dessus de cette classe
		//de service , les méthodes du dao réutilisent le entityManager et la transaction
		//déjà ouvert par le service métier
		//et les objets "cptDeb" et "cptCred" sont à l'état persistants.
		
		//à l'état persistant , toutes les modifications effectuées en mémoire
		//sont automatiquement répercutées en base lors du commit (lui même
		//automatique du fait de @Transactional).
	}

}
