package co.edu.udea.certificacion.sprint3.stepdefinitions;

import co.edu.udea.certificacion.sprint3.config.AppConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        
        // Limpiar sesión ANTES de cada escenario también
        WebDriver driver = Serenity.getWebdriverManager().getCurrentDriver();
        if (driver != null) {
            try {
                // Limpiar cookies
                driver.manage().deleteAllCookies();
                
                // Limpiar localStorage y sessionStorage
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("if(window.localStorage) { window.localStorage.clear(); }");
                js.executeScript("if(window.sessionStorage) { window.sessionStorage.clear(); }");
                
                // Navegar a la página de login para resetear
                driver.get(AppConfig.getLoginUrl());
                
                // Esperar un poco para que la página cargue
                Thread.sleep(1000);
                
                System.out.println("Hooks @Before: Sesión limpiada y navegando a login");
            } catch (Exception e) {
                System.out.println("Hooks @Before: " + e.getMessage());
            }
        }
    }

    @After
    public void tearDown() {
        // Obtener el driver del navegador actual
        WebDriver driver = Serenity.getWebdriverManager().getCurrentDriver();

        if (driver != null) {
            // 1. Borrar todas las cookies
            driver.manage().deleteAllCookies();

            // 2. Borrar LocalStorage y SessionStorage (CRÍTICO para tu app React)
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("if(window.localStorage) { window.localStorage.clear(); }");
                js.executeScript("if(window.sessionStorage) { window.sessionStorage.clear(); }");
                System.out.println("Hooks @After: Sesión limpiada correctamente (LocalStorage & Cookies)");
            } catch (Exception e) {
                System.out.println("Hooks @After: No se pudo limpiar el almacenamiento local: " + e.getMessage());
            }
        }
    }
}