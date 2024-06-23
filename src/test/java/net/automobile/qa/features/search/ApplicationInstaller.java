/*
package net.automobile.qa.features.search;

import net.automobile.AndroidObject;
import net.automobile.android.Visit;
import net.automobile.android.Switch;
import net.automobile.interactions.Wait;
import net.automobile.qa.questions.TheApp;
import net.automobile.qa.tasks.Close;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;

import java.time.Duration;

import static net.automobile.qa.ui.CHPlayUI.INSTALL;
import static net.automobile.qa.ui.CHPlayUI.PLAY;
import static net.automobile.qa.ui.HomePageUI.REGISTER;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

@RunWith(SerenityRunner.class)
public class ApplicationInstaller extends AndroidObject{


   @Test
    public void run() {


        try{
            
            androidUser.attemptsTo(
                    Visit.at("https://lucky88.vip/get-app"),
                    Close.theAdsPopUp(),

                    Wait.aBit(3),
                    WaitUntil.the(By.xpath("//img[contains(@src,'android.svg')]/parent::a"), isClickable()),
                    Click.on(By.xpath("//img[contains(@src,'android.svg')]/parent::a")),
                    Switch.to("NATIVE_APP"),
                    Click.on(INSTALL),
                    //Install.theApplication()
                    WaitUntil.the(PLAY, isClickable()).forNoMoreThan(Duration.ofMinutes(1)),
                    Ensure.that(TheApp.isInstalled()).isTrue(),
                    Click.on(PLAY),
                    WaitUntil.the(REGISTER, isClickable()).forNoMoreThan(Duration.ofMinutes(2)),
                    Click.on(REGISTER)
            );


        } catch (SessionNotCreatedException e){
            System.out.println("Device not found!");
            throw new RuntimeException();
        }
    }
}
*/
