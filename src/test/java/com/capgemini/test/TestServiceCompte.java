package com.capgemini.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.entity.Compte;
import com.capgemini.service.IServiceCompte;

@RunWith(SpringJUnit4ClassRunner.class) //avec spring-test dans pom.xml
@ContextConfiguration("/springConfig.xml") //charger une seule fois => test performant
//@ActiveProfiles("mysql")
public class TestServiceCompte {
	
	@Autowired
	private IServiceCompte serviceCompte; //à tester
	
	@Test
	public void testBonTransfert() {
		Compte cptDebAvant = this.serviceCompte.rechercherCompteParNumero(1L);
		Compte cptCredAvant = this.serviceCompte.rechercherCompteParNumero(2L);
		System.out.println("avant bon transfert :");
			System.out.println("\t cptDebAvant="+cptDebAvant);
			System.out.println("\t cptCredAvant="+cptCredAvant);
		this.serviceCompte.transferer(50, 1L, 2L);
		Compte cptDebApres = this.serviceCompte.rechercherCompteParNumero(1L);
		Compte cptCredApres = this.serviceCompte.rechercherCompteParNumero(2L);
		System.out.println("apres bon transfert :");
			System.out.println("\t cptDebApres="+cptDebApres);
			System.out.println("\t cptCredApres="+cptCredApres);
		Assert.assertEquals(cptDebApres.getSolde(), cptDebAvant.getSolde()-50, 0.001);
		Assert.assertEquals(cptCredApres.getSolde(), cptCredAvant.getSolde()+50, 0.001);
	}
	
	@Test
	public void testMauvaisTransfert() {
		Compte cptDebAvant = this.serviceCompte.rechercherCompteParNumero(1L);
		Compte cptCredAvant = this.serviceCompte.rechercherCompteParNumero(2L);
		System.out.println("avant mauvais transfert :");
			System.out.println("\t cptDebAvant="+cptDebAvant);
			System.out.println("\t cptCredAvant="+cptCredAvant);
		try {
			this.serviceCompte.transferer(50, 1L, -2L);
			Assert.fail("une exception aurait du être levee");
		} catch (Exception e) {
			System.out.println("erreur normale , le compte -2 n'existe pas " 
		                      + e.getMessage() );
		}
		Compte cptDebApres = this.serviceCompte.rechercherCompteParNumero(1L);
		Compte cptCredApres = this.serviceCompte.rechercherCompteParNumero(2L);
		System.out.println("apres mauvais transfert :");
			System.out.println("\t cptDebApres="+cptDebApres);
			System.out.println("\t cptCredApres="+cptCredApres);
		Assert.assertEquals(cptDebApres.getSolde(), cptDebAvant.getSolde()-0, 0.001);
		Assert.assertEquals(cptCredApres.getSolde(), cptCredAvant.getSolde()+0, 0.001);
	}
	
	@Test
	public void testRechercherCompteParNumeroQuiVaSuperBien() {
		Compte c1 = this.serviceCompte.rechercherCompteParNumero(1L);
		Assert.assertTrue(c1.getNumero()==1L);
		System.out.println("c1="+c1.toString());
	}
	
	@Test
	public void testRechercherComptesDuClient() {
		List<Compte> listeComptes = this.serviceCompte.rechercherComptesDuClient(1L);
		Assert.assertTrue(listeComptes.size()>=1);
		System.out.println("listeComptes="+listeComptes);
	}
}
