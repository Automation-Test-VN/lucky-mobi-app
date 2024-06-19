package net.automobile.qa.features.search;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import java.net.MalformedURLException;

public class ParallelTestRunner implements Runnable {

    private String deviceName;
    private String udid;
    private int systemPort;

    public ParallelTestRunner(String deviceName, String udid, int systemPort) {
        this.deviceName = deviceName;
        this.udid = udid;
        this.systemPort = systemPort;
    }

    @Override
    public void run() {
        AppiumDriverLocalService appium = AndroidSetup.getAppium();
        AppiumDriver driver = AndroidSetup.getDriver(appium, deviceName, udid, systemPort);
        Actor user = Actor.named("User on " + deviceName);
        user.can(BrowseTheWeb.with(driver));

        user.attemptsTo(
                OpenBrowser.at("https://www.google.com")
        );

        if (driver != null) {
            driver.quit();
        }
        if (appium.isRunning()) {
            appium.close();
        }

    }
}
