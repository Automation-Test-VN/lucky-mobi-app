package net.automobile.qa.tasks;

import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.Set;

public class Switch extends ProviderDriver<AndroidDriver> implements Task {

    private final String context;

    public Switch(String context){
        this.context = context;
    }

    public static Performable to(String context) {
        return Tasks.instrumented(Switch.class, context);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
/*        Set<String> contextNames = getDriver(actor).getContextHandles();

        for (String contextName : contextNames) {
            if (contextName.equals(context)) {
                getDriver(actor).context(contextName);
            }
        }*/

        for (String contextName : getDriver(actor).getContextHandles()) {
            System.out.println("====================" + contextName);
            if (contextName.contains("NATIVE_APP")) {
                getDriver(actor).context(contextName);
                break;
            }
        }
    }
}
