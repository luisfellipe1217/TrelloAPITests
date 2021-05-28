import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class TestAPI {
	
	private static  String apiToken = "b056fbc4609acfa4d3241ce1a6fe725bfa267badb3996abf518851b8db92d0ab";		
	 private static String apiKey = "f560501b8207ba3a8632de34b00e8a60";	

	 @Test
	 public void testApi() {
	Response response = RestAssured.request(Method.GET, "https://api.trello.com/1/members/me/boards?key="+ apiKey +"&token="+ apiToken);
		
	System.out.println(response.getBody().asString());	

	 }
	 
	 @Test
	 public void validarTEstes() {
		 
			String name = "TESTE APITRELLO";
		 
		 ArrayList<String> names = given()
				.when()
					.get("https://api.trello.com/1/search?key=" + apiKey +"&token="+ apiToken + "&query=" + name)
				.then()
					.statusCode(200)
					.extract().path("cards.desc.findAll{it.contains('Teste de Api para desafio SenseDia') }");		
			
			System.out.println(names);
	 }
}


