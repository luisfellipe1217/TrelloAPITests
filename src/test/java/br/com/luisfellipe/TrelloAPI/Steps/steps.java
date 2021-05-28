// Por questões de segurança, devem ser informados indivualmente o Token e a Key da Api para execução dos testes

package br.com.luisfellipe.TrelloAPI.Steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;

public class steps {

	private static String apiToken = ""; //Deve ser informado o Token do usuário trello
	private static String apiKey = ""; // Deve ser informado a key da api trello

	@Before
	public static void setup() {

		RestAssured.baseURI = "https://api.trello.com/";

	}

	@Dado("que estou com acesso ao Trello")
	public void queEstouComAcessoAoTrello() {
		given()
		.when()
			.get("/1/members/me/boards?key=" + apiKey + "&token=" + apiToken)
		.then()
		.statusCode(200);
	}

	@Quando("solicito para cadastrar um novo card")
	public void solicitoParaCadastrarUmNovoCard() {	}

	@Quando("preencho as informações necessárias {string}  {string}  {string}")
	public void preenchoAsInformaçõesNecessárias(String name, String descricao, String idList) {

		given().log().all().contentType("application/json")
			.body("{ \"name\" :  \"" + name + "\" , \"desc\": \"" + descricao + "\" , \"idList\": \"" + idList	+ "\"}")
		.when()
			.post("1/cards/?key=" + apiKey + "&token=" + apiToken)
		.then()
			.statusCode(200)
			.body("name", is(name));
	}

	@Então("é inserido um novo card ao meu quadro {string} {string} {string}")
	public void éInseridoUmNovoCardAoMeuQuadro(String name, String descricao, String idList) {
		given()
		.when()
			.get("1/search?key=" + apiKey + "&token=" + apiToken + "&query=" + name)
		.then()
			.statusCode(200)
			.body("cards.name.findAll{it.contains('" + name + "')}", hasItem(name))
			.body("cards.desc.findAll{it.contains('" + descricao + "')}", hasItem(descricao))
			.body("cards.idList.findAll{it.contains('" + idList + "')}", hasItem(idList));
	}

	@Quando("não preencho as informações obrigatórias")
	public void nãoPreenchoAsInformaçõesObrigatórias() {
		given().log().all()
			.contentType("application/json")
			.body(("{ \"name\" :  \"\" , \"desc\": \"\" , \"idList\": \"}"))
		.when()
				.post("1/cards/?key=" + apiKey + "&token=" + apiToken)
		.then().statusCode(400);
	}

	@Então("não deve ser inserido um novo card")
	public void nãoDeveSerInseridoUmNovoCard() {} //realizado no step acima

	@Dado("que tenho um card cadastrado {string}")
	public void queTenhoUmCardCadastrado(String idCard) {
		given()
		.when()
			.get("1/cards/" + idCard + "?key=" + apiKey + "&token=" + apiToken)
		.then()
			.statusCode(200);
	}

	@Quando("solicito uma alteração {string} {string} {string}")
	public void solicitoUmaAlteração(String idCard, String descricao, String name) { {
			given().log().all().contentType("application/json")
					.body("{ \"name\" :  \"" + name + "\" , \"desc\": \"" + descricao + "\"}")
			.when()
					.put("1/cards/" + idCard + "?key=" + apiKey + "&token=" + apiToken)
			.then()
				.statusCode(200)
			;
		}
	}

	@Quando("envio as informações a serem alteradas")
	public void envioAsInformaçõesASeremAlteradas() {}		

	@Então("é realizada a alteração no card. {string} {string} {string}")
	public void éRealizadaAAlteraçãoNoCard(String idCard, String descricao, String name) {
		given()
		.when()
			.get("1/cards/" + idCard + "?key=" + apiKey + "&token=" + apiToken)
		.then()
			.statusCode(200)
			.body("cards.name.findAll{it.contains('" + name + "')}", hasItem(name))
			.body("cards.desc.findAll{it.contains('" + descricao + "')}", hasItem(descricao))
		;	
	}
}
