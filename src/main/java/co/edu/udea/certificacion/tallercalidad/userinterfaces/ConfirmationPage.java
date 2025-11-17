package co.edu.udea.certificacion.tallercalidad.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ConfirmationPage {
    public static final Target THANK_YOU_MESSAGE = Target.the("Thank you header")
            .located(By.xpath("//h1[contains(text(),'Thank you')]"));
}