package co.edu.udea.certificacion.sprint3.stepdefinitions;

import co.edu.udea.certificacion.sprint3.questions.ElEnlaceDeNotificacion;
import co.edu.udea.certificacion.sprint3.questions.ElTituloDeNotificacion;
import co.edu.udea.certificacion.sprint3.questions.LaCategoriaDeNotificacion;
import co.edu.udea.certificacion.sprint3.tasks.NavegarAlModuloNotificaciones;
import co.edu.udea.certificacion.sprint3.tasks.RevisarBandejaEntrada;
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
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class NotificacionesStepDefinitions {

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("usuario");
    }

    @Dado("que he iniciado sesión exitosamente")
    public void queHeIniciadoSesionExitosamente() {
        theActorInTheSpotlight().wasAbleTo(
                Open.url("https://ejemplo.com/dashboard") // Cambiar por la URL real
        );
    }

    @Y("navego al módulo de notificaciones")
    public void navegoAlModuloDeNotificaciones() {
        theActorInTheSpotlight().attemptsTo(
                NavegarAlModuloNotificaciones.deUsuario()
        );
    }

    @Dado("que el sistema ha generado un evento de tipo {string}")
    public void queElSistemaHaGeneradoUnEventoDeTipo(String tipo) {
        // Aquí se podría simular la generación del evento
        theActorInTheSpotlight().remember("tipoEvento", tipo);
    }

    @Cuando("reviso mi bandeja de entrada")
    public void revisoMiBandejaDeEntrada() {
        theActorInTheSpotlight().attemptsTo(
                RevisarBandejaEntrada.deNotificaciones()
        );
    }

    @Entonces("la notificación debe tener el título {string}")
    public void laNotificacionDebeTenerElTitulo(String tituloEsperado) {
        theActorInTheSpotlight().should(
                seeThat("El título de la notificación",
                        ElTituloDeNotificacion.visible(), equalTo(tituloEsperado))
        );
    }

    @Y("debe mostrar el icono o etiqueta visual de {string}")
    public void debeMostrarElIconoOEtiquetaVisualDe(String categoriaEsperada) {
        theActorInTheSpotlight().should(
                seeThat("La categoría de la notificación",
                        LaCategoriaDeNotificacion.visible(), equalTo(categoriaEsperada))
        );
    }

    @Y("debe contener un enlace que dirige a {string}")
    public void debeContenerUnEnlaceQueDirigeA(String destinoEsperado) {
        theActorInTheSpotlight().should(
                seeThat("El enlace de la notificación",
                        ElEnlaceDeNotificacion.direccion(), containsString(destinoEsperado))
        );
    }
}

