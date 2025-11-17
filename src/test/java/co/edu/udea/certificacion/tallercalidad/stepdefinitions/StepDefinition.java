package co.edu.udea.certificacion.tallercalidad.stepdefinitions;

import co.edu.udea.certificacion.tallercalidad.questions.TheConfirmationMessage;
import co.edu.udea.certificacion.tallercalidad.tasks.FillOutPurchaseForm;
import co.edu.udea.certificacion.tallercalidad.tasks.SearchForFlights;
import co.edu.udea.certificacion.tallercalidad.tasks.SelectTheFirstFlight;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinition {

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
    }

    @Given("the user navigates to the BlazeDemo home page")
    public void navigateToHomePage() {
        theActorInTheSpotlight().wasAbleTo(Open.url("https://blazedemo.com/"));
    }

    @When("he searches for flights from {string} to {string}")
    public void searchForFlights(String origin, String destination) {
        theActorInTheSpotlight().attemptsTo(
                SearchForFlights.from(origin, destination)
        );
    }

    @When("he selects the first available flight")
    public void selectFirstFlight() {
        theActorInTheSpotlight().attemptsTo(
                SelectTheFirstFlight.fromList()
        );
    }

    @When("he completes the purchase form with the following data")
    public void completePurchaseForm(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        theActorInTheSpotlight().attemptsTo(
                FillOutPurchaseForm.withData(data)
        );
    }

    @Then("he should see the message {string}")
    public void verifyMessage(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat("Confirmation Message", TheConfirmationMessage.value(), equalTo(expectedMessage))
        );
    }
}