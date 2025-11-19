package co.edu.udea.certificacion.tallercalidad.tasks;

import co.edu.udea.certificacion.tallercalidad.interactions.TimeDelay;
import co.edu.udea.certificacion.tallercalidad.userinterfaces.ReservePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.core.pages.WebElementFacade; // <-- NUEVA IMPORTACIÓN
import org.openqa.selenium.By;
// Importación de List es necesaria
import java.util.List;
import java.util.Locale;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SelectTheMostExpensiveFlight implements Task {

    public static SelectTheMostExpensiveFlight fromList() {
        return Tasks.instrumented(SelectTheMostExpensiveFlight.class);
    }

    @Override
    @Step("{0} selects the most expensive flight available")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TimeDelay.of(3000),
                WaitUntil.the(ReservePage.FLIGHT_ROWS, isVisible())
        );

        List<WebElementFacade> flightRows = ReservePage.FLIGHT_ROWS.resolveAllFor(actor);
        double maxPrice = 0.0;


        for (WebElementFacade row : flightRows) {
            try {
                WebElementFacade priceElement = row.find(By.xpath("./td[6]"));

                // Clean the price string (remove '$' and commas)
                String priceText = priceElement.getText().replace("$", "").replace(",", "").trim();
                double currentPrice = Double.parseDouble(priceText);

                if (currentPrice > maxPrice) {
                    maxPrice = currentPrice;
                }
            } catch (Exception e) {
                // Ignore
            }
        }

        // Use the found maxPrice to construct a robust locator for the corresponding button
        if (maxPrice > 0.0) {
            String priceFormatted = String.format(Locale.US, "%.2f", maxPrice);

            Target finalButton = Target.the("Choose This Flight button for max price $" + priceFormatted)
                    .located(By.xpath("//table//tr[td[6][contains(text(), '" + priceFormatted + "')]]//input[@type='submit']"));

            actor.attemptsTo(
                    Click.on(finalButton)
            );
        } else {
            throw new RuntimeException("Could not find any flight to select.");
        }
    }
}