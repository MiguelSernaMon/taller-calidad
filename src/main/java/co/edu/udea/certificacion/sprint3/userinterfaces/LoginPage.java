package co.edu.udea.certificacion.sprint3.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final Target EMAIL_INPUT = Target.the("Campo de correo electrónico")
            .located(By.cssSelector("[data-testid='email-input']"));

    public static final Target PASSWORD_INPUT = Target.the("Campo de contraseña")
            .located(By.cssSelector("[data-testid='password-input']"));

    public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
            .located(By.cssSelector("[data-testid='login-button']"));

    public static final Target ERROR_MESSAGE = Target.the("Mensaje de error")
            .located(By.cssSelector(".text-destructive")); // Usando la clase de Tailwind de tu componente

    public static final Target LOGIN_PAGE_INDICATOR = Target.the("Indicador de página de login")
            .located(By.cssSelector("form"));
}