package co.edu.udea.certificacion.sprint3.questions;
import co.edu.udea.certificacion.sprint3.userinterfaces.NotificacionesPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Attribute;
public class ElEnlaceDeNotificacion implements Question<String> {
    public static ElEnlaceDeNotificacion direccion() {
        return new ElEnlaceDeNotificacion();
    }
    @Override
    public String answeredBy(Actor actor) {
        return Attribute.of(NotificacionesPage.NOTIFICACION_ENLACE)
                .named("href")
                .answeredBy(actor);
    }
}
