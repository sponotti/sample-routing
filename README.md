# sample-routing
“sample-routing” test

Environment:
- Tomcat 7
- Java 1.7
- Eclipse: Version: Luna Service Release 2 (4.4.2)

Implementation:

- Implemeted a REST service http://localhost:8080/sample-routing/countries that makes a call to the REST service https://restcountries.eu/rest/v2/all in order to return a list of countries and their currencies
	The controller class is com.sample.routing.controller.CountryController
- Implemented a page http://localhost:8080/sample-routing/countries-page that lists the countries and their currencies. For this I implemented a controller com.sample.routing.controller.CountryPageController and a JSP sample-routing\src\main\webapp\WEB-INF\pages\list.jsp
