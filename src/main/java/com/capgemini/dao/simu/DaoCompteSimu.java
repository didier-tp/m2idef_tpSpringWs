package com.capgemini.dao.simu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.capgemini.dao.IDaoCompte;
import com.capgemini.entity.Compte;
/*
 * version "simulation sans base de données"
 * du DaoCompte (proche de la notion de "Mock")
 */
@Component //id par defaut = "daoCompteSimu" (classe avec premiere lettre en minuscule)
//ou bien @Repository heritant de @Component (pour DAO)
public class DaoCompteSimu implements IDaoCompte {
	
	private Map<Long,Compte> mapComptes = new HashMap<Long,Compte>();
	
	public Long compteur=3L;
	
	public DaoCompteSimu() {
		mapComptes.put(1L, new Compte(1L,"compte 1", 110.0));
		mapComptes.put(2L, new Compte(2L,"compte 2", 220.0));
		mapComptes.put(3L, new Compte(3L,"compte 3", 330.0));
	}
	
	@Override
	public Compte findCompteByNum(Long numero) {
		return mapComptes.get(numero);
	}
	
	@Override
	public List<Compte> findComptesDuClient(Long numClient) {
		return new ArrayList<Compte>();//v1 : retourne liste vide
	}
	
	@Override
	public Compte createCompte(Compte c) {
		compteur++; //simuler auto_incr;
		c.setNumero(compteur);
		mapComptes.put(c.getNumero(), c);
		return c;
	}

	
	@Override
	public void updateCompte(Compte c) {
		mapComptes.put(c.getNumero(), c);
	}

	@Override
	public void deleteCompte(Compte c) {
		mapComptes.remove(c.getNumero());
	}


	

	

	
}
