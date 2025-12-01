package co.edu.udea.certificacion.sprint3.stepdefinitions;

import co.edu.udea.certificacion.sprint3.config.AppConfig;
import co.edu.udea.certificacion.sprint3.questions.ElMensajeDeError;
import co.edu.udea.certificacion.sprint3.questions.LaPaginaDeLogin;
import co.edu.udea.certificacion.sprint3.questions.ElNombreDeUsuario;
import co.edu.udea.certificacion.sprint3.questions.ElRolDeUsuario;
import co.edu.udea.certificacion.sprint3.questions.EstoyEnElDashboard;
import co.edu.udea.certificacion.sprint3.tasks.IngresarCredenciales;
import co.edu.udea.certificacion.sprint3.tasks.IntentarIniciarSesion;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1500)
        );
    }

    @Given("that I enter the email {string}")
    public void thatIEnterTheEmail(String email) {
        theActorInTheSpotlight().remember("correo", email);
    }

    @And("the password {string}")
    public void thePassword(String password) {
        theActorInTheSpotlight().remember("password", password);

        String correo = theActorInTheSpotlight().recall("correo");
        theActorInTheSpotlight().attemptsTo(
                IngresarCredenciales.con(correo, password),
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1000)
        );
    }

    @When("I attempt to log in")
    public void iAttemptToLogIn() {
        theActorInTheSpotlight().attemptsTo(
                IntentarIniciarSesion.enElSistema(),
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(2000)
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
        theActorInTheSpotlight().should(
                seeThat("La página de login está visible",
                        LaPaginaDeLogin.estaVisible(), is(true))
        );
    }

    @Then("I should be redirected to the dashboard")
    public void iShouldBeRedirectedToTheDashboard() {
        theActorInTheSpotlight().should(
                seeThat("Está en el dashboard",
                        EstoyEnElDashboard.correctamente(), is(true))
        );
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1500)
        );
    }

    @And("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat("El mensaje de error",
                        ElMensajeDeError.enLaPaginaDeLogin(), equalTo(expectedMessage))
        );
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1000)
        );
    }

    @And("I should see my username {string}")
    public void iShouldSeeMyUsername(String expectedName) {
        theActorInTheSpotlight().should(
                seeThat("El nombre de usuario",
                        ElNombreDeUsuario.enElDashboard(), equalTo(expectedName))
        );
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1000)
        );
    }

    @And("I should see my role as {string}")
    public void iShouldSeeMyRoleAs(String expectedRole) {
        theActorInTheSpotlight().should(
                seeThat("El rol del usuario",
                        ElRolDeUsuario.enElDashboard(), equalTo(expectedRole))
        );
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1000)
        );
    }
}



