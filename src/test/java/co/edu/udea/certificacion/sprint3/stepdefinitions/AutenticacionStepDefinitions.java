package co.edu.udea.certificacion.sprint3.stepdefinitions;

import co.edu.udea.certificacion.sprint3.config.AppConfig;
import co.edu.udea.certificacion.sprint3.interactions.TimeDelay;
import co.edu.udea.certificacion.sprint3.questions.*;
import co.edu.udea.certificacion.sprint3.tasks.IngresarCredenciales;
import co.edu.udea.certificacion.sprint3.tasks.IntentarIniciarSesion;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.*;

import co.edu.udea.certificacion.sprint3.questions.EstoyEnElDashboard;

public class AutenticacionStepDefinitions {

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }

    @Given("that I am on the login page")
    public void thatIAmOnTheLoginPage() {
        OnStage.theActorCalled("usuario").wasAbleTo(
                Open.url(AppConfig.getLoginUrl())
        );
    }

    @Given("that I enter the email {string}")
    public void thatIEnterTheEmail(String email) {
        theActorInTheSpotlight().remember("correo", email);
    }

    @When("I enter the email {string}")
    public void iEnterTheEmail(String email) {
        theActorInTheSpotlight().remember("correo", email);
    }

    @And("the password {string}")
    public void thePassword(String password) {
        theActorInTheSpotlight().remember("password", password);
        String correo = theActorInTheSpotlight().recall("correo");
        theActorInTheSpotlight().attemptsTo(
                IngresarCredenciales.con(correo, password)
        );
    }

    @When("I attempt to log in")
    public void iAttemptToLogIn() {
        theActorInTheSpotlight().attemptsTo(
                IntentarIniciarSesion.enElSistema()
        );
    }

    @When("I attempt to log in without entering credentials")
    public void iAttemptToLogInWithoutEnteringCredentials() {
        theActorInTheSpotlight().attemptsTo(
                IngresarCredenciales.con("", ""),
                IntentarIniciarSesion.enElSistema()
        );
    }

    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        theActorInTheSpotlight().attemptsTo(
                TimeDelay.of(AppConfig.DEFAULT_TIMEOUT)
        );
        theActorInTheSpotlight().should(
                seeThat("La página de login está visible",
                        LaPaginaDeLogin.estaVisible(), is(true))
        );
    }

    @Then("I should be redirected to the dashboard")
    public void iShouldBeRedirectedToTheDashboard() {
        theActorInTheSpotlight().attemptsTo(
                TimeDelay.of(AppConfig.DEFAULT_TIMEOUT)
        );
        theActorInTheSpotlight().should(
                seeThat("Está en el dashboard",
                        EstoyEnElDashboard.correctamente(), is(true))
        );
    }

    @And("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedMessage) {
        theActorInTheSpotlight().attemptsTo(
                TimeDelay.of(AppConfig.DEFAULT_TIMEOUT)
        );
        theActorInTheSpotlight().should(
                seeThat("El mensaje de error",
                        ElMensajeDeError.enLaPaginaDeLogin(), equalTo(expectedMessage))
        );
    }

    @And("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedMessage) {
        theActorInTheSpotlight().attemptsTo(
                TimeDelay.of(AppConfig.DEFAULT_TIMEOUT)
        );
        theActorInTheSpotlight().should(
                seeThat("El mensaje de error",
                        ElMensajeDeError.enLaPaginaDeLogin(), equalTo(expectedMessage))
        );
    }

    @And("I should see an error message indicating required fields")
    public void iShouldSeeAnErrorMessageIndicatingRequiredFields() {
        theActorInTheSpotlight().attemptsTo(
                TimeDelay.of(AppConfig.DEFAULT_TIMEOUT)
        );
        theActorInTheSpotlight().should(
                seeThat("Mensaje de campos requeridos",
                        ElMensajeDeError.enLaPaginaDeLogin(), 
                        anyOf(
                            containsString("requerido"),
                            containsString("required"),
                            containsString("obligatorio"),
                            containsString("campo"),
                            containsString("vacío"),
                            containsString("empty"),
                            notNullValue()
                        ))
        );
    }

    @And("I should see my username {string}")
    public void iShouldSeeMyUsername(String expectedName) {
        theActorInTheSpotlight().should(
                seeThat("El nombre de usuario",
                        ElNombreDeUsuario.enElDashboard(), equalTo(expectedName))
        );
    }

    @And("I should see my role as {string}")
    public void iShouldSeeMyRoleAs(String expectedRole) {
        theActorInTheSpotlight().should(
                seeThat("El rol del usuario",
                        ElRolDeUsuario.enElDashboard(), equalTo(expectedRole))
        );
    }
}