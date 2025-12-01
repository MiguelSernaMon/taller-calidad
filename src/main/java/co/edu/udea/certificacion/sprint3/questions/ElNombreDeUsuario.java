package co.edu.udea.certificacion.sprint3.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class ElNombreDeUsuario implements Question<String> {

    // Selector para el nombre del usuario en el dashboard
    private static final Target NOMBRE_USUARIO = Target.the("nombre del usuario")
            .locatedBy("[data-testid='user-name']");

    public static ElNombreDeUsuario enElDashboard() {
        return new ElNombreDeUsuario();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(NOMBRE_USUARIO).answeredBy(actor);
    }
}
