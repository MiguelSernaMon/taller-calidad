package co.edu.udea.certificacion.sprint3.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/authentication.feature",
        glue = "co.edu.udea.certificacion.sprint3.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class AutenticacionRunner {}


