package net.automobile.qa.features.search;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.SessionNotCreatedException;

public class ParallelTestRunner implements Runnable {

    private String deviceName;
    private String udid;
    private int systemPort;
    private AppiumDriverLocalService appium;
    private AppiumDriver driver ;

    public ParallelTestRunner(String deviceName, String udid, int systemPort) {
        this.deviceName = deviceName;
        this.udid = udid;
        this.systemPort = systemPort;
    }

    @Override
    public void run() {
        try{
            appium = AndroidSetup.getAppium();
            driver = AndroidSetup.getDriver(appium, deviceName, udid, systemPort);
            Actor user = Actor.named("User on " + deviceName);
            user.can(BrowseTheWeb.with(driver));

            user.attemptsTo(
                    OpenBrowser.at("https://www.google.com")
            );
        } catch (SessionNotCreatedException e){
            System.out.println("Device not found!");
        } finally {
            if (driver != null) {
                driver.quit();
            }

            if (appium.isRunning()) {
                appium.close();
            }
        }



    }
}
