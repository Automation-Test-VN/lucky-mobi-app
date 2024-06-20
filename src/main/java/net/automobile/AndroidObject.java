package net.automobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.RELAXED_SECURITY;

public class AndroidObject {

    public final static String BUNDLE_ID = "com.luckynumberbouncingball";

    protected static AndroidDriver driver;
    protected AppiumDriverLocalService appium;
    protected Actor androidUser = Actor.named("Android");

    protected String[][] devices = {
            {"Samsung S23 Plus","R5CW82ST0AL","8201"}
    };

    public static AndroidDriver getDriver(AppiumDriverLocalService appium, String deviceName, String udid, int systemPort) {
        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options()
                    .autoGrantPermissions()
                    .setUdid(udid)
                    .setSystemPort(systemPort)
                    .setDeviceName(deviceName)
                    .eventTimings()
                    .setAdbExecTimeout(Duration.ofMinutes(3))
                    .amend("browserName", "Chrome");

            driver = new AndroidDriver(appium.getUrl(), options);
        }
        return driver;
    }

    public static AppiumDriverLocalService getAppium() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withArgument(RELAXED_SECURITY);
        builder.usingAnyFreePort();

        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
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

        if (appium!=null) {
            appium.close();
        }

        ProcessBuilder process = new ProcessBuilder("taskkill", "/F", "/IM", "node.exe");
        try {
            process.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
