package distributed;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import net.serenitybdd.core.pages.PageObject;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.RELAXED_SECURITY;

public class BaseTest extends PageObject {
    private static AppiumDriver driver;

    public static AppiumDriver setupDriver(AppiumDriverLocalService appiumLocal, String udid){

        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options()
                    .autoGrantPermissions()
                    .setUdid(udid)
                    .amend("browserName", "Chrome");

            driver = new AppiumDriver(appiumLocal.getUrl(), options);
        }
        return driver;
    }

    public static AppiumDriverLocalService setupAppium(){
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
