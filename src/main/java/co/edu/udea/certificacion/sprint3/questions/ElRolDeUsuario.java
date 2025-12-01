package co.edu.udea.certificacion.sprint3.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class ElRolDeUsuario implements Question<String> {

    // Selector para el rol del usuario en el dashboard
    private static final Target ROL_USUARIO = Target.the("rol del usuario")
            .locatedBy("[data-testid='user-role']");

    public static ElRolDeUsuario enElDashboard() {
        return new ElRolDeUsuario();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(ROL_USUARIO).answeredBy(actor);
    }
}
