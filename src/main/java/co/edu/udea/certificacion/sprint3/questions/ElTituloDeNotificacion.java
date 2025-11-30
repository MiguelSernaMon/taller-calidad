package co.edu.udea.certificacion.sprint3.questions;
import co.edu.udea.certificacion.sprint3.userinterfaces.NotificacionesPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
public class ElTituloDeNotificacion implements Question<String> {
    public static ElTituloDeNotificacion visible() {
        return new ElTituloDeNotificacion();
    }
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(NotificacionesPage.NOTIFICACION_TITULO).answeredBy(actor);
    }
}
