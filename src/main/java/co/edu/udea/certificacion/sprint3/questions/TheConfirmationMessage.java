package co.edu.udea.certificacion.tallercalidad.questions;

import co.edu.udea.certificacion.tallercalidad.userinterfaces.ConfirmationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheConfirmationMessage implements Question<String> {

    public static TheConfirmationMessage value() {
        return new TheConfirmationMessage();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(ConfirmationPage.THANK_YOU_MESSAGE).answeredBy(actor);
    }
}