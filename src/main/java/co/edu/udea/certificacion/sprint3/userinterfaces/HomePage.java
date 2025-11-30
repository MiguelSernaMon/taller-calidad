package co.edu.udea.certificacion.tallercalidad.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {
    public static final Target FROM_PORT_DROPDOWN = Target.the("Origin city dropdown")
            .located(By.name("fromPort"));
    public static final Target TO_PORT_DROPDOWN = Target.the("Destination city dropdown")
            .located(By.name("toPort"));
    public static final Target FIND_FLIGHTS_BUTTON = Target.the("Find Flights button")
            .located(By.cssSelector("input[type='submit']"));
}