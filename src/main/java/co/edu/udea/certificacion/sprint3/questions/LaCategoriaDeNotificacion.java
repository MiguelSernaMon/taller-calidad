package co.edu.udea.certificacion.sprint3.questions;
import co.edu.udea.certificacion.sprint3.userinterfaces.NotificacionesPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
public class LaCategoriaDeNotificacion implements Question<String> {
    public static LaCategoriaDeNotificacion visible() {
        return new LaCategoriaDeNotificacion();
    }
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(NotificacionesPage.NOTIFICACION_CATEGORIA).answeredBy(actor);
    }
}
