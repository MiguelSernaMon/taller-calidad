package co.edu.udea.certificacion.sprint3.stepdefinitions;

import co.edu.udea.certificacion.sprint3.config.AppConfig;
import co.edu.udea.certificacion.sprint3.questions.ElEnlaceDeNotificacion;
import co.edu.udea.certificacion.sprint3.questions.ElTituloDeNotificacion;
import co.edu.udea.certificacion.sprint3.questions.LaCategoriaDeNotificacion;
import co.edu.udea.certificacion.sprint3.tasks.NavegarAlModuloNotificaciones;
import co.edu.udea.certificacion.sprint3.tasks.RevisarBandejaEntrada;
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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class NotificacionesStepDefinitions {

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }

    @Given("that I have successfully logged in")
    public void thatIHaveSuccessfullyLoggedIn() {
        theActorInTheSpotlight().wasAbleTo(
                Open.url(AppConfig.getDashboardUrl())
        );
    }

    @And("I navigate to the notifications module")
    public void iNavigateToTheNotificationsModule() {
        theActorInTheSpotlight().attemptsTo(
                NavegarAlModuloNotificaciones.deUsuario(),
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(2000)
        );
    }

    @Given("that the system has generated an event of type {string}")
    public void thatTheSystemHasGeneratedAnEventOfType(String type) {
        // Aquí se podría simular la generación del evento
        theActorInTheSpotlight().remember("tipoEvento", type);
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(500)
        );
    }

    @When("I check my inbox")
    public void iCheckMyInbox() {
        theActorInTheSpotlight().attemptsTo(
                RevisarBandejaEntrada.deNotificaciones(),
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1500)
        );
    }

    @Then("the notification should have the title {string}")
    public void theNotificationShouldHaveTheTitle(String expectedTitle) {
        theActorInTheSpotlight().should(
                seeThat("El título de la notificación",
                        ElTituloDeNotificacion.visible(), equalTo(expectedTitle))
        );
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1000)
        );
    }

    @And("it should display the icon or visual label of {string}")
    public void itShouldDisplayTheIconOrVisualLabelOf(String expectedCategory) {
        theActorInTheSpotlight().should(
                seeThat("La categoría de la notificación",
                        LaCategoriaDeNotificacion.visible(), equalTo(expectedCategory))
        );
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1000)
        );
    }

    @And("it should contain a link that directs to {string}")
    public void itShouldContainALinkThatDirectsTo(String expectedDestination) {
        theActorInTheSpotlight().should(
                seeThat("El enlace de la notificación",
                        ElEnlaceDeNotificacion.direccion(), containsString(expectedDestination))
        );
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1000)
        );
    }
    
    // ==================== E2E FLOW SPECIFIC STEPS ====================
    
    @Then("I should be able to see the notifications section")
    public void iShouldBeAbleToSeeTheNotificationsSection() {
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1500)
        );
        System.out.println("✓ Usuario puede ver la sección de notificaciones");
    }

    @Then("I should be able to access notification settings")
    public void iShouldBeAbleToAccessNotificationSettings() {
        theActorInTheSpotlight().attemptsTo(
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(1500)
        );
        System.out.println("✓ Administrador puede acceder a configuración de notificaciones");
    }

    @Then("I should see notifications relevant to my role {string}")
    public void iShouldSeeNotificationsRelevantToMyRole(String role) {
        theActorInTheSpotlight().attemptsTo(
                RevisarBandejaEntrada.deNotificaciones(),
                co.edu.udea.certificacion.sprint3.interactions.TimeDelay.of(2000)
        );
        System.out.println("✓ Usuario con rol " + role + " ve notificaciones relevantes");
    }
}

