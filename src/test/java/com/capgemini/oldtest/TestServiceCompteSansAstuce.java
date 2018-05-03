package com.capgemini.oldtest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.entity.Compte;
import com.capgemini.service.IServiceCompte;

public class TestServiceCompteSansAstuce {
	private IServiceCompte serviceCompte; //à tester
	private ApplicationContext springContext; //qui aide à ecrire le test via spring
	
	@Before
	public void initTestSpringQueJaimeLeVendredi() {		
		springContext =  new ClassPathXmlApplicationContext("/springConfig.xml");
		this.serviceCompte = springContext.getBean(IServiceCompte.class);
	}
	
	@Test
	public void testRechercherCompteParNumeroQuiVaSuperBien() {
		Compte c1 = this.serviceCompte.rechercherCompteParNumero(1L);
		Assert.assertTrue(c1.getNumero()==1L);
		System.out.println("c1="+c1.toString());
	}
	
	@After
	public void bienTerminer() {
		((ClassPathXmlApplicationContext) springContext).close();
	}
}
