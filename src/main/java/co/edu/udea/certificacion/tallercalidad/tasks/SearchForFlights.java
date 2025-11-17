package co.edu.udea.certificacion.tallercalidad.tasks;

import co.edu.udea.certificacion.tallercalidad.interactions.TimeDelay;
import co.edu.udea.certificacion.tallercalidad.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

public class SearchForFlights implements Task {
    private final String origin;
    private final String destination;

    public SearchForFlights(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public static SearchForFlights from(String origin, String destination) {
        return Tasks.instrumented(SearchForFlights.class, origin, destination);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TimeDelay.of(3000), // Wait to see the home page
                SelectFromOptions.byVisibleText(origin).from(HomePage.FROM_PORT_DROPDOWN),
                SelectFromOptions.byVisibleText(destination).from(HomePage.TO_PORT_DROPDOWN),
                TimeDelay.of(1000), // Little wait before clicking
                Click.on(HomePage.FIND_FLIGHTS_BUTTON)
        );
    }
}