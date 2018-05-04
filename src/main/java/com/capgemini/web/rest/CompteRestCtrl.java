package com.capgemini.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Compte;
import com.capgemini.service.IServiceCompte;
import com.capgemini.web.rest.data.OrdreVirement;

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
	
	//sera appelé en HTTP / POST avec l' URL suivante:
	// http://localhost:8080/tpSpringWs/mvc/rest/compte/virement
	//avec en entrée { "montant" : 50.0 , "numCptDeb" : 1 , "numCptCred" : 2 } 
	//en retour on renvoie l'ordre de virement comportant en plus  , "ok" : true , "message" : "virement effectue" }
	@RequestMapping(value="/virement" , method=RequestMethod.POST)
	ResponseEntity<OrdreVirement> postOrdreVirement(@RequestBody OrdreVirement ordreVirement){
		
		try {
			serviceCompte.transferer(ordreVirement.getMontant(),
									 ordreVirement.getNumCptDeb(), 
									 ordreVirement.getNumCptCred());
			ordreVirement.setOk(true);
			ordreVirement.setMessage("virement bien effectue");
			return new ResponseEntity<OrdreVirement>(ordreVirement, HttpStatus.OK);
		} catch (Exception e) {
			ordreVirement.setOk(false);
			ordreVirement.setMessage("echec virement " + e.getMessage());
			return new ResponseEntity<OrdreVirement>(ordreVirement, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
