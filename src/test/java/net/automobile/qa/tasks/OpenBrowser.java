package net.automobile.qa.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

public class OpenBrowser implements Task {

    private final String url;

    public OpenBrowser(String url) {
        this.url = url;
    }

    public static OpenBrowser at(String url) {
        return Tasks.instrumented(OpenBrowser.class, url);
    }

    @Step("Open browser at {0}")
    public <T extends Actor> void performAs(T actor) {
//        AndroidDriver driver = (AndroidDriver) ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver()).getProxiedDriver();
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.get(url);
    }
}

