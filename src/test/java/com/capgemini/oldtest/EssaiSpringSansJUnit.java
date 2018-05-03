package com.capgemini.oldtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.entity.Compte;
import com.capgemini.service.IServiceCompte;

public class EssaiSpringSansJUnit {

	public static void main(String[] args) {
		ApplicationContext springContext;
		springContext = 
				new ClassPathXmlApplicationContext("/springConfig.xml");
		
		// serviceCompteImpl = id du service  dans springConfig.xml
		//IServiceCompte serviceCompte = (IServiceCompte) 
		//		springContext.getBean("serviceCompteImpl");
		
		IServiceCompte serviceCompte =  springContext.getBean(IServiceCompte.class);
		
        Compte c1 = serviceCompte.rechercherCompteParNumero(1L);
        System.out.println("c1="+c1.toString());
        
        ((ClassPathXmlApplicationContext) springContext).close();
	}

}
