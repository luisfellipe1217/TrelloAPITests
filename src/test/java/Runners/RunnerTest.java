package Runners;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/",
		glue = "br.com.luisfellipe.TrelloAPI.Steps",
		tags = "not @ignore",
		plugin = {"pretty", "html:Results/Report/",
				"json:target/cucumber.json"},
		monochrome = false, 
		snippets = io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE,
	 	dryRun = true,
	 	publish = true
	 	) 

public class RunnerTest {
	
}
