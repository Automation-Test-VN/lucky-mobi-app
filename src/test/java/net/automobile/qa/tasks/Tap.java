package net.automobile.qa.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

public class Tap implements Task {
    private Target target;

    public Tap( Target target){
        this.target = target;
    }

    public static Tap to(Target target) {
        return Tasks.instrumented(Tap.class,target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        int x = target.resolveFor(actor).getLocation().x;
        int y = target.resolveFor(actor).getLocation().y;
//        tap(x,y);
        System.out.println(x);
        System.out.println(y);
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
