package co.edu.udea.certificacion.tallercalidad.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class TimeDelay implements Interaction {
    private final int milliseconds;

    public TimeDelay(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public static TimeDelay of(int milliseconds) {
        return Tasks.instrumented(TimeDelay.class, milliseconds);
    }

    @Override
    @Step("{0} waits for #milliseconds milliseconds")
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}