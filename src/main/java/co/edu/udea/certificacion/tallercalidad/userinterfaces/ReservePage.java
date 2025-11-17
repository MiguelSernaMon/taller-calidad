package co.edu.udea.certificacion.tallercalidad.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ReservePage {
    // Gets the first button in the list
    public static final Target FIRST_FLIGHT_BUTTON = Target.the("Choose This Flight button")
            .located(By.xpath("(//input[@type='submit'])[1]"));
}