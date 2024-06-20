package net.automobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.After;

import java.util.Map;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.RELAXED_SECURITY;

public class AndroidObject {
    protected static AndroidDriver driver;
    public static final String APP_ID = "io.appium.android.apis";
    protected AppiumDriverLocalService appium;
    protected static final int PORT = 4723;

    public static AndroidDriver getDriver(AppiumDriverLocalService appium, String deviceName, String udid, int systemPort) {
        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options()
                    .autoGrantPermissions()
                    .setUdid(udid)
                    .setSystemPort(systemPort)
                    .setDeviceName(deviceName)
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

    public static void startActivity(String name) {
        driver.executeScript(
                "mobile: startActivity",
                Map.of("component", name)
        );
    }

    public static void terminateApp(String name) {
        driver.executeScript("mobile: terminateApp", Map.of("appId", APP_ID));
    }

    public static void activateApp(String name) {
        driver.executeScript("mobile: activateApp", Map.of("appId", APP_ID));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (appium!=null) {
            appium.close();
        }
    }

}
