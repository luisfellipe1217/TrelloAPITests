// Por questões de segurança, devem ser informados indivualmente o Token e a Key da Api para execução dos testes

package br.com.luisfellipe.TrelloAPI.Steps;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;



public class steps {

	public static String apiToken = ""; //Deve ser informado o Token do usuário trello
	public static String apiKey = ""; // Deve ser informado a key da api trello
	public static String idCard;


	@Before
	public static void setup() {
		RestAssured.baseURI = "https://api.trello.com/";
	}

	@Dado("que estou com acesso ao Trello")
	public void queEstouComAcessoAoTrello() {
		given()
			.log().method()
			.log().uri()			
		.when()
			.get("1/members/me/boards?key={yourKey}&token={yourToken}", apiKey ,apiToken)
		.then()
		.statusCode(200);  //valida statusCode se requisição está acontecendo com sucesso.
	}

	@Quando("solicito para cadastrar um novo card")
	public void solicitoParaCadastrarUmNovoCard() {	}

	@Quando("preencho as informações necessárias {string}  {string}  {string}")
	public void preenchoAsInformaçõesNecessárias(String name, String descricao, String idList) {
		String id = 
		 given()
			.log().method()
			.log().uri()						
			.contentType("application/json")
			.body("{ \"name\" :  \"" + name + "\" , \"desc\": \"" + descricao + "\" , \"idList\": \"" + idList	+ "\"}")
		.when()
			.post("1/cards/?key={yourKey}&token={yourToken}", apiKey,  apiToken)
	        .prettyPeek().jsonPath().getString("id").toString()	        
		;		
			
			idCard = id;					
	}

	@Então("é inserido um novo card ao meu quadro {string} {string} {string}")
	public void éInseridoUmNovoCardAoMeuQuadro(String name, String descricao, String idList) {
		given()	
			.log().method()
			.log().uri()
			.log().body()
		.when()
			.get("1/cards/{id}?key={yourKey}&token={youtToken}", idCard, apiKey, apiToken)			
		.then()			
			.statusCode(200)  //valida statusCode se requisição está acontecendo com sucesso.
			.body("name", Matchers.is(name)) //Assertiva para validar se a pesquisa de cards contem o nome do card cadastrado
			.body("desc", Matchers.is(descricao)) //Assertiva para validar se contém a descricao cadastrada
			.body("idList", Matchers.is(idList)); //Assertiva para validar se contém o idList igual ao card cadastrado.
	}

	@Quando("não preencho as informações obrigatórias")
	public void nãoPreenchoAsInformaçõesObrigatórias() {
		given()		
			.log().method()
			.log().uri()
			.log().body()			
			.contentType("application/json")
			.body(("{ \"name\" :  \"\" , \"desc\": \"\" , \"idList\": \"}"))
		.when()
			.post("1/cards/?key={yourKey}&token={yourToken}", apiKey,  apiToken) 
		.then()
			.statusCode(400);  //valida statusCode se requisição está como esperada
	}

	@Então("não deve ser inserido um novo card")
	public void nãoDeveSerInseridoUmNovoCard() {} //realizado no step acima

	@Dado("que tenho um card cadastrado")
	public void queTenhoUmCardCadastrado() {
		given()
			.log().method()
			.log().uri()
		.when()		
			.get("1/cards/{id}?key={yourKey}&token={yourToken}",  idCard, apiKey, apiToken)
		.then()
			.statusCode(200); //valida statusCode se requisição está acontecendo com sucesso.
	}

	@Quando("solicito uma alteração  {string} {string}")
	public void solicitoUmaAlteração(String descricao, String name) { {
			given()					
					.log().method()
					.log().uri()
					.log().body()
					.contentType("application/json")
					.body("{ \"name\" :  \"" + name + "\" , \"desc\": \"" + descricao + "\"}")
			.when()
					.put("1/cards/{id}?key={yourKey}&token={yourToken}", idCard , apiKey , apiToken)
			.then()
				.statusCode(200)  //valida statusCode se requisição está acontecendo com sucesso.
			;
		}
	}

	@Quando("envio as informações a serem alteradas")
	public void envioAsInformaçõesASeremAlteradas() {}		

	@Então("é realizada a alteração no card. {string} {string}")
	public void éRealizadaAAlteraçãoNoCard( String descricao, String name) {
		given()
			.log().method()
			.log().uri()			
		.when()
			.get("1/cards/{id}?key={yourKey}&token={yourToken}", idCard , apiKey , apiToken) 
		.then()
			.statusCode(200)  //valida statusCode se requisição está acontecendo com sucesso.
			.body("name", Matchers.is(name)) //Assertiva para validar se a pesquisa de cards contem o nome do card alterado
			.body("desc",Matchers. is(descricao)) //Assertiva para validar se a pesquisa de cards contem a descricao do card alterado
		;	
	}	
}
