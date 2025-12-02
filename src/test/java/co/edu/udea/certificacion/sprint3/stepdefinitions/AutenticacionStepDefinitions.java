package co.edu.udea.certificacion.sprint3.stepdefinitions;

import co.edu.udea.certificacion.sprint3.config.AppConfig;
import co.edu.udea.certificacion.sprint3.questions.*;
import co.edu.udea.certificacion.sprint3.tasks.IngresarCredenciales;
import co.edu.udea.certificacion.sprint3.tasks.IntentarIniciarSesion;
// Importaciones necesarias para WaitUntil
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

// Necesitas importar el Target del Dashboard para esperarlo
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
                // Asegúrate que AppConfig.getLoginUrl() devuelve la URL correcta (Vercel o localhost)
                Open.url(AppConfig.getLoginUrl())
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
        theActorInTheSpotlight().should(
                seeThat("La página de login está visible",
                        LaPaginaDeLogin.estaVisible(), is(true))
        );
    }

    // CORRECCIÓN PRINCIPAL AQUÍ:
    @Then("I should be redirected to the dashboard")
    public void iShouldBeRedirectedToTheDashboard() {
        // 1. Esperar hasta 10 segundos a que el dashboard sea visible.
        // Esto maneja el delay de 800ms de tu API mock.
        // Nota: Necesitas exponer el Target en tu clase EstoyEnElDashboard o crear uno aquí.
        // Asumiendo que puedes acceder a un Target que identifique el dashboard:

        // Opcion A: Usar un TimeDelay ANTES (menos elegante pero funciona rápido)
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(2000)
        );

        // 2. AHORA SÍ verificamos
        theActorInTheSpotlight().should(
                seeThat("Está en el dashboard",
                        EstoyEnElDashboard.correctamente(), is(true))
        );
    }

    @And("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedMessage) {
        // Esperar un poco a que aparezca el error (animación)
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1000)
        );

        theActorInTheSpotlight().should(
                seeThat("El mensaje de error",
                        ElMensajeDeError.enLaPaginaDeLogin(), equalTo(expectedMessage))
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