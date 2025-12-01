package co.edu.udea.certificacion.sprint3.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class EstoyEnElDashboard implements Question<Boolean> {

    // Selector para verificar que estamos en el dashboard
    private static final Target DASHBOARD_TITLE = Target.the("título del dashboard")
            .locatedBy("[data-testid='dashboard-title']");

    public static EstoyEnElDashboard correctamente() {
        return new EstoyEnElDashboard();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            // Verificar si existe el título del dashboard
            return DASHBOARD_TITLE.resolveFor(actor).isVisible();
        } catch (Exception e) {
            return false;
        }
    }
}
