package net.automobile.qa.features.junit;

import net.automobile.AndroidObject;
import net.automobile.Fake;
import net.automobile.ISP;
import net.automobile.android.Activate;
import net.automobile.android.Switch;
import net.automobile.android.Visit;
import net.automobile.interactions.Wait;
import net.automobile.qa.questions.TheApp;
import net.automobile.qa.tasks.Close;
import net.automobile.qa.tasks.Install;
import net.automobile.qa.ui.RegisterUI;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import java.time.Duration;

import static net.automobile.qa.ui.CHPlayUI.PLAY;
import static net.automobile.qa.ui.HomePageUI.REGISTER;
import static net.automobile.qa.ui.RegisterUI.*;
import static net.automobile.qa.ui.RegisterUI.TEXT;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

@RunWith(SerenityRunner.class)
@WithTag("junit")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestApplicationInstallerStore extends AndroidObject{

    Actor jacob = Actor.named("Jacob");

    @Before
    public void set() {
        driver = getDriver(ISP.FPT.getDeviceName(),ISP.FPT.getUDID());
        jacob.can(BrowseTheWeb.with(driver));
    }
    @Test
    public void ShouldSeeTheApplicationIsInstalledSuccessful() {

            jacob.attemptsTo(
                    Visit.at("https://lucky88.vip/get-app"),
                    Close.theAdsPopUp(),

                    Wait.aBit(3),
                    WaitUntil.the(By.xpath("//img[contains(@src,'android.svg')]/parent::a"), isClickable()),
                    Click.on(By.xpath("//img[contains(@src,'android.svg')]/parent::a")),
                    Install.theApplication(),
                    Ensure.that(TheApp.isInstalled()).isTrue(),
                    Click.on(PLAY),
                    WaitUntil.the(REGISTER, isClickable()).forNoMoreThan(Duration.ofMinutes(2)),
                    Click.on(REGISTER)
            );
    }

    @Test
    public void ShouldSeeTheNewAccountIsRegisteredSuccessful() {

        Ensure.enableSoftAssertions();

        String pwd = Fake.password();
        jacob.attemptsTo(
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
    }
}
