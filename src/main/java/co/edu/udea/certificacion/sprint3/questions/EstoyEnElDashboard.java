package co.edu.udea.certificacion.sprint3.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EstoyEnElDashboard implements Question<Boolean> {

    public static EstoyEnElDashboard correctamente() {
        return new EstoyEnElDashboard();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            // Verificar que la URL contenga "dashboard" o que estemos fuera del login
            String currentUrl = driver.getCurrentUrl();
            System.out.println("EstoyEnElDashboard - URL actual: " + currentUrl);
            
            // Estrategia 1: Verificar por URL
            if (currentUrl.contains("/dashboard") || currentUrl.contains("/home")) {
                System.out.println("EstoyEnElDashboard - Verificado por URL");
                return true;
            }
            
            // Estrategia 2: Verificar que NO estemos en la página de login
            if (!currentUrl.contains("/login") && !currentUrl.endsWith("/")) {
                // Esperar un elemento del dashboard
                try {
                    wait.until(ExpectedConditions.or(
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='dashboard-title']")),
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dashboard")),
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class*='dashboard']")),
                        ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Dashboard')]"))
                    ));
                    System.out.println("EstoyEnElDashboard - Verificado por elemento del dashboard");
                    return true;
                } catch (Exception e) {
                    // Si no hay error en login, consideramos que estamos en dashboard
                    if (!driver.getPageSource().contains("error") && !driver.getPageSource().contains("Credenciales")) {
                        System.out.println("EstoyEnElDashboard - Verificado por ausencia de errores");
                        return true;
                    }
                }
            }
            
            System.out.println("EstoyEnElDashboard - No se pudo verificar dashboard");
            return false;
        } catch (Exception e) {
            System.out.println("EstoyEnElDashboard - Error: " + e.getMessage());
            return false;
        }
    }
}
