package net.automobile.interactions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class Wait {
    public static Performable aBit(long seconds) {
        return Task.where("{0} wait a bit in " + seconds + " seconds", actor -> {
            try {
                Thread.sleep(seconds*1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
