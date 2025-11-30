package co.edu.udea.certificacion.tallercalidad.tasks;

import co.edu.udea.certificacion.tallercalidad.interactions.TimeDelay;
import co.edu.udea.certificacion.tallercalidad.userinterfaces.PurchasePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import java.util.Map;

public class FillOutPurchaseForm implements Task {

    private final Map<String, String> data;

    public FillOutPurchaseForm(Map<String, String> data) {
        this.data = data;
    }

    public static FillOutPurchaseForm withData(Map<String, String> data) {
        return Tasks.instrumented(FillOutPurchaseForm.class, data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TimeDelay.of(3000), // Wait to see the form
                Enter.theValue(data.get("name")).into(PurchasePage.NAME_INPUT),
                Enter.theValue(data.get("address")).into(PurchasePage.ADDRESS_INPUT),
                Enter.theValue(data.get("city")).into(PurchasePage.CITY_INPUT),
                Enter.theValue(data.get("state")).into(PurchasePage.STATE_INPUT),
                Enter.theValue(data.get("zipCode")).into(PurchasePage.ZIP_CODE_INPUT),
                SelectFromOptions.byVisibleText(data.get("cardType")).from(PurchasePage.CARD_TYPE_DROPDOWN),
                Enter.theValue(data.get("creditCardNumber")).into(PurchasePage.CREDIT_CARD_NUMBER_INPUT),
                Enter.theValue(data.get("month")).into(PurchasePage.MONTH_INPUT),
                Enter.theValue(data.get("year")).into(PurchasePage.YEAR_INPUT),
                Enter.theValue(data.get("nameOnCard")).into(PurchasePage.NAME_ON_CARD_INPUT),
                TimeDelay.of(1000),
                Click.on(PurchasePage.PURCHASE_FLIGHT_BUTTON)
        );
    }
}