package net.automobile.qa.tasks;

import net.automobile.adb.Tap;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class Close {

    public static Performable theAdsPopUp() {
        return Task.where(
                Tap.on(284,562));
    }
}
