package net.automobile.qa.features.search;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import net.automobile.qa.tasks.OpenBrowser;
import net.automobile.qa.tasks.Switch;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;

public class ParallelTestRunner implements Runnable {

    private String deviceName;
    private String udid;
    private int systemPort;
    private AppiumDriverLocalService appium;
    private AndroidDriver driver ;

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

            Actor webUser = Actor.named("Open web " + udid);
            webUser.can(BrowseTheWeb.with(driver));

            webUser.attemptsTo(
                    OpenBrowser.at("https://lucky88.vip/get-app")
            );
            Thread.sleep(3000);

            tap(284,562);


            webUser.attemptsTo(
                    Click.on(By.xpath("//img[contains(@src,'android.svg')]/parent::a"))
                    //Switch.to("NATIVE_APP")
            );

            for (String contextName : driver.getContextHandles()) {
                if (contextName.contains("NATIVE_APP")) {
                    driver.context(contextName);
                    break;
                }
            }
/*
            driver.executeScript(
                    "mobile: startActivity",
                    Map.of(
                            "component", String.format("%s/%s", "com.android.vending", "com.google.android.finsky.activities.MainActivity")
                    )
            );*/

            driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Install']")).click();


        } catch (SessionNotCreatedException e){
            System.out.println("Device not found!");
            throw new RuntimeException();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (driver != null) {
                driver.quit();
            }

            if (appium.isRunning()) {
                appium.close();
            }
        }
    }

    public static void tap(int x,int y) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("adb", "shell", "input", "tap", String.valueOf(x) ,String.valueOf(y));
            Process process = processBuilder.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
