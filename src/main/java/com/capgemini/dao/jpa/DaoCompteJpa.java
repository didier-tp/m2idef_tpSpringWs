package com.capgemini.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.IDaoCompte;
import com.capgemini.entity.Client;
import com.capgemini.entity.Compte;

/*
 * classe d'implémentation du DAO qui 
 * s'appuie sur JPA/Hibernate
 */
@Component
@Transactional //pour que spring declenche automatiquement commit/rollback
public class DaoCompteJpa implements IDaoCompte {
	
	@PersistenceContext //pour initialiser le entityManager avec Spring et jpa 
	                    // selon META-INF/persistence.xml
	private EntityManager entityManager;

	@Override
	public Compte findCompteByNum(Long numero) {
		return entityManager.find(Compte.class, numero); //select by id/pk
	}

	@Override
	public Compte createCompte(Compte c) {
		//avant le .persist() , c.getNumero() vaut null
		entityManager.persist(c);//insert into avec eventuel auto_incr
		//apres le .persist() , c.getNumero() vaut la valeur auto incrementée par mysql
		return c;
	}

	@Override
	public void updateCompte(Compte c) {
		entityManager.merge(c); //update where id/pk =...
	}

	@Override
	public void deleteCompte(Compte c) {
		entityManager.remove(c);//delete sql
	}
	
	@Override
	public List<Compte> findComptesDuClient(Long numClient) {
		/*
		//solution 1 (exploitant le lien @OneToMany):
		Client cli = entityManager.find(Client.class, numClient);
		List<Compte> comptesDuClient = cli.getComptes();
		comptesDuClient.size(); //pour eviter "lazy exception" coté web ou coté test
		return comptesDuClient;
		*/
		/*
		//solution 2:
		return entityManager.createQuery(
				"SELECT cpt FROM Client cli INNER JOIN cli.comptes cpt " 
				+ " WHERE cli.numero  = :numCli",Compte.class)
				   .setParameter("numCli", numClient)
				   .getResultList(); */
		//solution 3 (uniquement basé sur le lien @ManyToOne):
		return entityManager.createQuery(
				"SELECT cpt FROM Compte cpt WHERE cpt.client.numero = :numCli",Compte.class)
				   .setParameter("numCli", numClient)
				   .getResultList();
				   
	}

}
