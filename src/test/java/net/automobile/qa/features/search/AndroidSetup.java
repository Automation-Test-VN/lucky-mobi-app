package net.automobile.qa.features.search;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.net.UrlChecker;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.RELAXED_SECURITY;

public class AndroidSetup {

    private static AppiumDriver driver;

    public static AppiumDriver getDriver(AppiumDriverLocalService appium, String deviceName,String  udid,int  systemPort){
        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options()
                    .autoGrantPermissions()
                    .setUdid(udid)
                    .setSystemPort(systemPort)
                    .setDeviceName(deviceName)
                    .amend("browserName", "Chrome");

            driver = new AppiumDriver(appium.getUrl(), options);
        }
        return driver;
    }

    public static AppiumDriverLocalService getAppium(){
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withArgument(RELAXED_SECURITY);
        builder.usingAnyFreePort();

        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info");

        AppiumDriverLocalService appiumLocal = builder.build();
        appiumLocal.start();

        return appiumLocal;

    }
}
