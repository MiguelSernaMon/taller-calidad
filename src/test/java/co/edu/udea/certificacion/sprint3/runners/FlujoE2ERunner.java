package co.edu.udea.certificacion.sprint3.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Runner para el flujo E2E completo
 * Ejecuta el flujo completo: Login → Funcionalidades
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/flujo_e2e_completo.feature",
        glue = "co.edu.udea.certificacion.sprint3.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"}
)
public class FlujoE2ERunner {}
