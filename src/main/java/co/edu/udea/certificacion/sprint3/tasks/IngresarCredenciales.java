package co.edu.udea.certificacion.sprint3.tasks;

import co.edu.udea.certificacion.sprint3.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;

public class IngresarCredenciales implements Task {

    private final String correo;
    private final String password;

    public IngresarCredenciales(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }

    public static IngresarCredenciales con(String correo, String password) {
        return Tasks.instrumented(IngresarCredenciales.class, correo, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (correo != null && !correo.isEmpty()) {
            actor.attemptsTo(
                    Enter.theValue(correo).into(LoginPage.EMAIL_INPUT)
            );
        }
        if (password != null && !password.isEmpty()) {
            actor.attemptsTo(
                    Enter.theValue(password).into(LoginPage.PASSWORD_INPUT)
            );
        }
    }
}


