package co.edu.udea.certificacion.sprint3.questions;

import co.edu.udea.certificacion.sprint3.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class LaPaginaDeLogin implements Question<Boolean> {

    public static LaPaginaDeLogin estaVisible() {
        return new LaPaginaDeLogin();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return Visibility.of(LoginPage.LOGIN_PAGE_INDICATOR).answeredBy(actor);
    }
}
