package Runners;

//Por questões de segurança, devem ser informados indivualmente o Token e a Key da Api para execução dos testes nos steps.

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

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
	
}
