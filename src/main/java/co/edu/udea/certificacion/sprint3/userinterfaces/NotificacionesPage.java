package co.edu.udea.certificacion.sprint3.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class NotificacionesPage {

    public static final Target MODULO_NOTIFICACIONES = Target.the("Módulo de notificaciones")
            .located(By.id("notifications"));

    public static final Target NOTIFICACION_TITULO = Target.the("Título de la notificación")
            .located(By.cssSelector(".notification-title"));

    public static final Target NOTIFICACION_CATEGORIA = Target.the("Categoría de la notificación")
            .located(By.cssSelector(".notification-category"));

    public static final Target NOTIFICACION_ENLACE = Target.the("Enlace de la notificación")
            .located(By.cssSelector(".notification-link"));

    public static final Target BANDEJA_ENTRADA = Target.the("Bandeja de entrada")
            .located(By.cssSelector(".inbox"));
}
