package net.automobile.qa.tasks;

import net.automobile.android.Switch;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.automobile.qa.ui.CHPlayUI.INSTALL;
import static net.automobile.qa.ui.CHPlayUI.PLAY;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class Install {

    public static Performable theApplication() {
        return Task.where(
                Switch.to("NATIVE_APP"),
                Click.on(INSTALL),
                WaitUntil.the(PLAY, isClickable()).forNoMoreThan(Duration.ofMinutes(1)));
    }
}