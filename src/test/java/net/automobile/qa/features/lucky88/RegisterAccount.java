package net.automobile.qa.features.lucky88;

import net.automobile.AndroidObject;
import net.automobile.Fake;
import net.automobile.android.Activate;
import net.automobile.android.Switch;
import net.automobile.qa.ui.RegisterUI;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.SessionNotCreatedException;

import java.time.Duration;

import static net.automobile.qa.ui.HomePageUI.REGISTER;
import static net.automobile.qa.ui.RegisterUI.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class RegisterAccount extends AndroidObject implements Runnable {

    private final String deviceName;
    private final String udid;
    private final int systemPort;

    public RegisterAccount(String deviceName, String udid, int systemPort) {
        this.deviceName = deviceName;
        this.udid = udid;
        this.systemPort = systemPort;
    }

    @Override
    public void run() {

        try{
            appium = getAppium();
            driver = getDriver(appium, deviceName, udid, systemPort);
            androidUser.can(BrowseTheWeb.with(driver));

            Ensure.enableSoftAssertions();

            String pwd = Fake.password();
            androidUser.attemptsTo(
                    Activate.theApp(BUNDLE_ID),
                    Switch.to("NATIVE_APP"),
                    WaitUntil.the(REGISTER, isClickable()).forNoMoreThan(Duration.ofMinutes(2)),
                    Click.on(REGISTER),
                    Enter.theValue(Fake.username()).into(RegisterUI.USER_NAME),
                    Enter.theValue(pwd).into(RegisterUI.PASSWORD),
                    Enter.theValue(pwd).into(RegisterUI.CONFIRM_PWD),
                    Enter.theValue(Fake.phoneNumber()).into(RegisterUI.PHONE_NUMBER),
                    Click.on(RegisterUI.NEXT_BUTTON),
                    Click.on(LUCKY_NUMBER.of("7")),
                    Click.on(DONE),
                    Ensure.that(TEXT.of("XIN CHÚC MỪNG!")).isDisplayed(),
                    Ensure.that(TEXT.of("BẠN ĐÃ LÀ MỘT THÀNH VIÊN CỦA LUCKY88")).isDisplayed(),
                    Ensure.that(TEXT.of("Nạp tiền ngay để chơi game")).isDisplayed(),
                    Ensure.that(TEXT.of("NẠP TIỀN")).isDisplayed(),
                    Ensure.that(TEXT.of("TRANG CHỦ")).isDisplayed()
            );

            Ensure.reportSoftAssertions();


        } catch (SessionNotCreatedException e){
            System.out.println("Device not found!");
            throw new RuntimeException();
        }
    }
}
