package co.edu.udea.certificacion.sprint3.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class NotificacionesPage {

    // En tu Sidebar.tsx, el link a notificaciones debería tener un test-id o usamos el href
    public static final Target MODULO_NOTIFICACIONES = Target.the("Módulo de notificaciones")
            .located(By.cssSelector("a[href='/notifications']"));

    // Estos selectores deben buscar dentro de la tarjeta visible
    public static final Target NOTIFICACION_TITULO = Target.the("Título de la notificación")
            .located(By.cssSelector("[data-testid='notification-title']"));

    public static final Target NOTIFICACION_CATEGORIA = Target.the("Categoría de la notificación")
            .located(By.cssSelector("[data-testid='notification-category']"));

    // En tu NotificationCard.tsx, el botón "Ver detalles" es un botón con texto
    public static final Target NOTIFICACION_ENLACE = Target.the("Enlace de la notificación")
            .located(By.xpath("//button[contains(text(), 'Ver detalles')]"));

    public static final Target BANDEJA_ENTRADA = Target.the("Bandeja de entrada")
            .located(By.cssSelector("[data-testid='notifications-section']"));
}