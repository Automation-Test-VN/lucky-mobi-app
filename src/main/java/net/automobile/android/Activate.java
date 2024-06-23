package net.automobile.android;

import net.automobile.AndroidObject;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class Activate extends AndroidObject {

    public static Performable theApp(String bundleId){
        return Task.where(actor -> {
            driver.activateApp(bundleId);
        });
    }
}
