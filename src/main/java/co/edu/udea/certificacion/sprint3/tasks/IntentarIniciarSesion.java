package co.edu.udea.certificacion.sprint3.tasks;

import co.edu.udea.certificacion.sprint3.interactions.TimeDelay;
import co.edu.udea.certificacion.sprint3.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IntentarIniciarSesion implements Task {

    public static IntentarIniciarSesion enElSistema() {
        return Tasks.instrumented(IntentarIniciarSesion.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                // Esperar a que el botón esté visible y clickeable
                WaitUntil.the(LoginPage.LOGIN_BUTTON, isVisible()).forNoMoreThan(10).seconds(),
                WaitUntil.the(LoginPage.LOGIN_BUTTON, isClickable()).forNoMoreThan(10).seconds(),
                // Pequeño delay antes de hacer clic
                TimeDelay.of(500),
                // Hacer clic en el botón
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}

