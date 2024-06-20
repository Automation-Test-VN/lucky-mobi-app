package net.automobile.qa.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class HomePageUI {
    public final static Target REGISTER = Target.the("register button")
            .locatedForAndroid(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(17)"))
            .locatedForIOS(AppiumBy.xpath("sdf"));
}
