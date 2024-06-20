package net.automobile.android;

import net.automobile.AndroidObject;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class Visit extends AndroidObject implements Task {

    private final String url;

    public Visit(String url) {
        this.url = url;
    }

    public static Visit at(String url) {
        return Tasks.instrumented(Visit.class, url);
    }

    @Step("Open browser at {0}")
    public <T extends Actor> void performAs(T actor) {
        driver.get(url);
    }
}

