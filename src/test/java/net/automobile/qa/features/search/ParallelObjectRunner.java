package net.automobile.qa.features.search;

import io.appium.java_client.AppiumBy;
import net.automobile.AndroidObject;
import net.automobile.adb.Tap;
import net.automobile.android.Visit;
import net.automobile.android.Switch;
import net.automobile.interactions.Wait;
import net.automobile.qa.tasks.Close;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class ParallelObjectRunner extends AndroidObject implements Runnable {

    private final String deviceName;
    private final String udid;
    private final int systemPort;

    public ParallelObjectRunner(String deviceName, String udid, int systemPort) {
        this.deviceName = deviceName;
        this.udid = udid;
        this.systemPort = systemPort;
    }

    final static Target INSTALL = Target.the("install button")
            .locatedForAndroid(AppiumBy.xpath("//android.widget.TextView[@content-desc='Install']"))
            .locatedForIOS(AppiumBy.xpath("sdf"));


    @Override
    public void run() {

        try{
            appium = getAppium();
            driver = getDriver(appium, deviceName, udid, systemPort);

            Actor androidUser = Actor.named("Android user");
            androidUser.can(BrowseTheWeb.with(driver));

            androidUser.attemptsTo(
                    Visit.at("https://lucky88.vip/get-app"),
                    Close.theAdsPopUp(),

                    Wait.aBit(3),
                    WaitUntil.the(By.xpath("//img[contains(@src,'android.svg')]/parent::a"), isClickable()),
                    Click.on(By.xpath("//img[contains(@src,'android.svg')]/parent::a")),
                    Switch.to("NATIVE_APP"),
                    Click.on(INSTALL)
                    //Install.theApplication()
            );


        } catch (SessionNotCreatedException e){
            System.out.println("Device not found!");
            throw new RuntimeException();
        }
    }
}
