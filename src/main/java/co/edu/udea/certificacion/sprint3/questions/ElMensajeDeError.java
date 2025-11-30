package co.edu.udea.certificacion.sprint3.questions;
import co.edu.udea.certificacion.sprint3.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
public class ElMensajeDeError implements Question<String> {
    public static ElMensajeDeError enLaPaginaDeLogin() {
        return new ElMensajeDeError();
    }
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(LoginPage.ERROR_MESSAGE).answeredBy(actor);
    }
}
