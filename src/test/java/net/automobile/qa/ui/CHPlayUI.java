package net.automobile.qa.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class CHPlayUI {
    public final static Target INSTALL = Target.the("install button")
            .locatedForAndroid(AppiumBy.xpath("//android.widget.TextView[@content-desc='Install']"))
            .locatedForIOS(AppiumBy.xpath("sdf"));

    public final static Target PLAY = Target.the("play button")
            .locatedForAndroid(AppiumBy.xpath("//android.widget.TextView[@content-desc='Play']"))
            .locatedForIOS(AppiumBy.xpath("sdf"));
}
