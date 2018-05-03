package com.capgemini.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.IDaoClient;
import com.capgemini.entity.Client;
import com.capgemini.entity.Compte;
/*
 * classe d'implémentation du DAO qui 
 * s'appuie sur JPA/Hibernate
 */
@Component
@Transactional //pour que spring declenche automatiquement commit/rollback
public class DaoClientJpa implements IDaoClient {

	@PersistenceContext //pour initialiser le entityManager avec Spring et jpa 
    // selon META-INF/persistence.xml
	private EntityManager entityManager;

	
	@Override
	public Client findClientByNum(Long numero) {
		return entityManager.find(Client.class, numero); //select by id/pk
	}

	@Override
	public List<Client> findAllClients() {
		return entityManager.createQuery("SELECT c FROM Client c",Client.class)
				//.setParameter(...,...)
				.getResultList();
	}

	@Override
	public Client createClient(Client c) {
		entityManager.persist(c);//insert into avec eventuel auto_incr
		return c;
	}

	@Override
	public void updateClient(Client c) {
		entityManager.merge(c); //update where id/pk =...
	}

	@Override
	public void deleteClient(Client c) {
		entityManager.remove(c);//delete sql
	}

}
