package co.edu.udea.certificacion.sprint3.tasks;

import co.edu.udea.certificacion.sprint3.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class IntentarIniciarSesion implements Task {

    public static IntentarIniciarSesion enElSistema() {
        return Tasks.instrumented(IntentarIniciarSesion.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}

