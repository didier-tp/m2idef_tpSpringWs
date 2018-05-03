package com.capgemini.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Compte;
import com.capgemini.service.IServiceCompte;

@RestController
@RequestMapping(value="/rest/compte" , 
                headers="Accept=application/json")
public class CompteRestCtrl {
	
	//business service vers lequel déléguer les traitements :
	@Autowired
	private IServiceCompte serviceCompte; 
	
	//sera appelé en HTTP / GET avec l' URL suivante:
	// http://localhost:8080/tpSpringWs/mvc/rest/compte/duClient?numClient=1
	@RequestMapping(value="/duClient" , method=RequestMethod.GET)
	List<Compte> getComptesDuClient( @RequestParam("numClient") Long numClient ){
		return serviceCompte.rechercherComptesDuClient(numClient);
		//NB: le resultat java de type List<Compte>
		//sera automatiquement transformé au format JSON
	}
	
	//+ virement en mode POST ...

}
