package net.automobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.RELAXED_SECURITY;

public class AndroidObject {

    @Managed
    protected static AndroidDriver driver;

    protected Actor androidUser = Actor.named("Android user");


    @Before
    public void tearUp() {
        driver = getDriver("Samsung S23 Plus", "R5CW82ST0AL", 8201);
        androidUser.can(BrowseTheWeb.with(driver));
    }


    public static AndroidDriver getDriver(String deviceName, String udid, int systemPort) {
        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options()
                    .autoGrantPermissions()
                    .setUdid(udid)
                    .setSystemPort(systemPort)
                    .setDeviceName(deviceName)
                    .eventTimings()
                    .setAdbExecTimeout(Duration.ofMinutes(1))
                    .amend("browserName", "Chrome");

            driver = new AndroidDriver(getAppium().getUrl(), options);
        }
        return driver;
    }

    public static AppiumDriverLocalService getAppium() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(RELAXED_SECURITY)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info");

        AppiumDriverLocalService appiumLocal = builder.build();
        appiumLocal.start();

        return appiumLocal;
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

/*        if (appium != null) {
            appium.close();
        }*/

        ProcessBuilder process = new ProcessBuilder("taskkill", "/F", "/IM", "node.exe");
        try {
            process.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
