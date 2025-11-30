package co.edu.udea.certificacion.tallercalidad.runners.runnerspackage;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/flight_booking.feature",
        glue = "co.edu.udea.certificacion.tallercalidad.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class FlightBookingRunner {}