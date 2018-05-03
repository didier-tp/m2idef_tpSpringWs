package com.capgemini.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.IDaoClient;
import com.capgemini.entity.Client;
/*
 * classe d'implémentation du service
 * qui ne fonctionne qu'avec un composant dao en arrière plan
 * ce dao est une "dépendance" à injecter et à utiliser pour déléguer
 */
//@Component
@Service() //@Service herite de @Component
//id/name par defaut = serviceClientImpl (nom classe avec miniscule au debut)
@Transactional
public class ServiceClientImpl implements IServiceClient{
	
	@Autowired
	private IDaoClient daoClient; //null par défaut 

	@Override
	public Client rechercherClientParNumero(Long numero) {
		return daoClient.findOne(numero);
	}

	@Override
	public List<Client> rechercherTousClient() {
		return (List<Client>) daoClient.findAll();
	}

	@Override
	public Client creerClient(Client c) {
		return daoClient.save(c);//au sens saveOrUpdate()
	}

	@Override
	public void modifierClient(Client c) {
		daoClient.save(c);//au sens saveOrUpdate()
	}

	@Override
	public void supprimerClient(long numClient) {
		daoClient.delete(numClient);
	}

}
