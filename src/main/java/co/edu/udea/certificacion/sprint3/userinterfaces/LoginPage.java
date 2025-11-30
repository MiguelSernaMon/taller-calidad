package co.edu.udea.certificacion.sprint3.userinterfaces;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
public class LoginPage {
    public static final Target EMAIL_INPUT = Target.the("Campo de correo electrónico")
            .located(By.id("email"));
    public static final Target PASSWORD_INPUT = Target.the("Campo de contraseña")
            .located(By.id("password"));
    public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
            .located(By.id("loginButton"));
    public static final Target ERROR_MESSAGE = Target.the("Mensaje de error")
            .located(By.cssSelector(".error-message"));
    public static final Target LOGIN_PAGE_INDICATOR = Target.the("Indicador de página de login")
            .located(By.cssSelector(".login-container"));
}
