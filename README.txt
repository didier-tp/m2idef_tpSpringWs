Dans ce projet cohabitent:
   - Spring (avec "dao / spring-data" et java-config @Configuration)
   - JSF
   - Spring-web-MVC (configuré en XML) avec @RestController
   
La partie web (index.html) est un point d'entrée vers 2 parties
bien distinctes :
   - welcome.jsf 
   - comptes_jquery_rest_json.html et virement_ajax.html (appels de WS-REST)
   