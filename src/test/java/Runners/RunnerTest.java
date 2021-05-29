package Runners;

import static io.restassured.RestAssured.given;
import br.com.luisfellipe.TrelloAPI.Steps.*;

import org.junit.AfterClass;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;


//Por questões de segurança, devem ser informados indivualmente o Token e a Key da Api para execução dos testes na classe steps.

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/",
		glue = "br.com.luisfellipe.TrelloAPI.Steps",
		tags = "not @ignore",
		plugin = {"pretty", "html:Results/Report/",
				"json:target/cucumber.json"},
		monochrome = true, 
		snippets = io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE,
	 	dryRun = false,
	 	publish = true
	 	) 

public class RunnerTest {

	
	@AfterClass
	public static void limparCenarios() {		
		
		RestAssured.baseURI = "https://api.trello.com/";
		
		given()
			.log().all()		
		.when()
			.delete("1/cards/{id}?key={yourKey}&token={yourToken}", steps.idCard , steps.apiKey , steps.apiToken) 			
		.then()
			.statusCode(200)
	;
		given()		
			.log().method()
			.log().uri()			
		.when()
			.get("1/cards/{id}?key={yourKey}&token={yourToken}", steps.idCard , steps.apiKey , steps.apiToken) 
		.then()
		.statusCode(404)
	;
}
}

