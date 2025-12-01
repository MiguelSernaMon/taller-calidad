package co.edu.udea.certificacion.sprint3.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    
    public static final Target EMAIL_INPUT = Target.the("Campo de correo electrónico")
            .located(By.id("email"));
    
    public static final Target PASSWORD_INPUT = Target.the("Campo de contraseña")
            .located(By.id("password"));
    
    // Botón de login con múltiples estrategias de localización
    public static final Target LOGIN_BUTTON = Target.the("Botón de inicio de sesión")
            .locatedBy("#loginButton, button[type='submit'], .login-button, button:contains('Iniciar'), button:contains('Login')");
    
    // Alternativa por XPath si el anterior no funciona
    public static final Target LOGIN_BUTTON_ALT = Target.the("Botón de inicio de sesión (alternativo)")
            .located(By.xpath("//button[@type='submit'] | //button[contains(text(), 'Iniciar')] | //button[contains(text(), 'Login')] | //input[@type='submit']"));
    
    public static final Target ERROR_MESSAGE = Target.the("Mensaje de error")
            .located(By.cssSelector(".error-message, .alert-danger, [class*='error']"));
    
    public static final Target LOGIN_PAGE_INDICATOR = Target.the("Indicador de página de login")
            .located(By.cssSelector(".login-container, .login-page, form[class*='login']"));
}
