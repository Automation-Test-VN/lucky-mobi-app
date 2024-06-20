package net.automobile.adb;

import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.targets.Target;

public class Tap implements Task {
    private final Target target;

    public Tap( Target target){
        this.target = target;
    }

    public static Tap on(Target target) {
        return Tasks.instrumented(Tap.class,target);
    }

    public static Performable on(int _x, int _y) {
        return Task.where("{0} tap on the element at coordination", actor -> {
            tap(_x, _y);
        });
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        int x = target.resolveFor(actor).getLocation().x;
        int y = target.resolveFor(actor).getLocation().y;
        tap(x,y);
    }

    public static void tap(int x,int y) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("adb", "shell", "input", "tap", String.valueOf(x) ,String.valueOf(y));
            Process process = processBuilder.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
