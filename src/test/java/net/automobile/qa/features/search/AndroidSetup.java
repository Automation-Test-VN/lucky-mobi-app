package net.automobile.qa.features.search;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.RELAXED_SECURITY;

public class AndroidSetup {

    private static AppiumDriver driver;

    public static AppiumDriver getDriver(){

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withArgument(RELAXED_SECURITY);
        builder.usingAnyFreePort();

        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info");

        AppiumDriverLocalService appiumLocal = builder.build();
        appiumLocal.start();

        String appiumServiceUrl = appiumLocal.getUrl().toString();

        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName("R5CW82ST0AL")
                    .autoGrantPermissions()
                    .amend("browserName", "Chrome");

            try {
                driver = new AppiumDriver(new URL(appiumServiceUrl), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }
}
