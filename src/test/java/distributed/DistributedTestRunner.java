package distributed;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import net.automobile.qa.features.search.OpenBrowser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class DistributedTestRunner implements Runnable {

    private String udid;
    private String url;

    public DistributedTestRunner(String udid, String url) {
        this.udid = udid;
        this.url = url;
    }

    @Override
    public void run() {

        AppiumDriverLocalService service = BaseTest.setupAppium();
        AppiumDriver driver = BaseTest.setupDriver(service,udid);

        try {
            Actor user = Actor.named("User: " + udid);
            user.can(BrowseTheWeb.with(driver));

            user.attemptsTo(
                    OpenBrowser.at(url)
            );

        } finally {
            if (driver != null) {
                driver.quit();
            }

            if (service.isRunning()) {
                service.stop();
            }
        }
    }
}
