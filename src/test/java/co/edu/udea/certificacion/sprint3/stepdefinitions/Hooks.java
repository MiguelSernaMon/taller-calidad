package co.edu.udea.certificacion.sprint3.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/**
 * Hooks globales para configuración de pruebas
 * Se ejecuta antes de cada escenario
 */
public class Hooks {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
