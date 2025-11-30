package co.edu.udea.certificacion.sprint3.tasks;

import co.edu.udea.certificacion.sprint3.userinterfaces.NotificacionesPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class NavegarAlModuloNotificaciones implements Task {

    public static NavegarAlModuloNotificaciones deUsuario() {
        return Tasks.instrumented(NavegarAlModuloNotificaciones.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(NotificacionesPage.MODULO_NOTIFICACIONES)
        );
    }
}

