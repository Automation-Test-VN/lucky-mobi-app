//package net.automobile.qa.features.search;
//
//import io.appium.java_client.AppiumDriver;
//import net.serenitybdd.annotations.Managed;
//import net.serenitybdd.junit.runners.SerenityRunner;
//import net.serenitybdd.screenplay.Actor;
//import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//import java.util.Collection;
//import java.util.List;
//
//
//@RunWith(SerenityRunner.class)
//public class SearchByKeywordStory {
//
//    Actor anna = Actor.named("Anna");
//
//    @Managed
//    public AppiumDriver herBrowser;
//
//    @Before
//    public void annaCanBrowseTheWeb(){
//        herBrowser = AndroidSetup.getDriver();
//        anna.can(BrowseTheWeb.with(herBrowser));
//    }
//
//    @Test
//    public void search_results_should_show_the_search_term_in_the_title() {
//
//        anna.attemptsTo(
//                OpenBrowser.at("https://sunwin.uk")
//        );
//    }
//}