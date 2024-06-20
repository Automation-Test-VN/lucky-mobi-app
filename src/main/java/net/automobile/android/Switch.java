package net.automobile.android;

import net.automobile.AndroidObject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class Switch extends AndroidObject implements Task {

    private final String context;

    public Switch(String context){
        this.context = context;
    }

    public static Performable to(String context) {
        return Tasks.instrumented(Switch.class, context);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        for (String contextName : AndroidObject.driver.getContextHandles()) {
            if (contextName.contains(context)) {
                AndroidObject.driver.context(contextName);
                break;
            }
        }
    }
}
