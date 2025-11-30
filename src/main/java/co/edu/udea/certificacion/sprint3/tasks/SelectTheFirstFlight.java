package co.edu.udea.certificacion.tallercalidad.tasks;

import co.edu.udea.certificacion.tallercalidad.interactions.TimeDelay;
import co.edu.udea.certificacion.tallercalidad.userinterfaces.ReservePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class SelectTheFirstFlight implements Task {

    public static SelectTheFirstFlight fromList() {
        return Tasks.instrumented(SelectTheFirstFlight.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TimeDelay.of(3000), // Wait to see the results
                Click.on(ReservePage.FIRST_FLIGHT_BUTTON)
        );
    }
}