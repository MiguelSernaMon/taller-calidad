package co.edu.udea.certificacion.sprint3.stepdefinitions;

import co.edu.udea.certificacion.sprint3.questions.ElMensajeDeError;
import co.edu.udea.certificacion.sprint3.questions.LaPaginaDeLogin;
import co.edu.udea.certificacion.sprint3.tasks.IngresarCredenciales;
import co.edu.udea.certificacion.sprint3.tasks.IntentarIniciarSesion;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
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

    @Dado("que me encuentro en la página de inicio de sesión")
    public void queMeEncuentroEnLaPaginaDeInicioSesion() {
        theActorInTheSpotlight().wasAbleTo(
                Open.url("https://ejemplo.com/login") // Cambiar por la URL real
        );
    }

    @Dado("que ingreso el correo {string}")
    public void queIngresoElCorreo(String correo) {
        theActorInTheSpotlight().remember("correo", correo);
    }

    @Y("la contraseña {string}")
    public void laContrasena(String password) {
        theActorInTheSpotlight().remember("password", password);

        String correo = theActorInTheSpotlight().recall("correo");
        theActorInTheSpotlight().attemptsTo(
                IngresarCredenciales.con(correo, password)
        );
    }

    @Cuando("intento iniciar sesión")
    public void intentoIniciarSesion() {
        theActorInTheSpotlight().attemptsTo(
                IntentarIniciarSesion.enElSistema()
        );
    }

    @Entonces("debería permanecer en la página de login")
    public void deberiaPermanecerEnLaPaginaDeLogin() {
        theActorInTheSpotlight().should(
                seeThat("La página de login está visible",
                        LaPaginaDeLogin.estaVisible(), is(true))
        );
    }

    @Y("debería ver el mensaje de error {string}")
    public void deberiaVerElMensajeDeError(String mensajeEsperado) {
        theActorInTheSpotlight().should(
                seeThat("El mensaje de error",
                        ElMensajeDeError.enLaPaginaDeLogin(), equalTo(mensajeEsperado))
        );
    }
}


