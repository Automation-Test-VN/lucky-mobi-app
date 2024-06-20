package net.automobile.qa.questions;

import net.automobile.AndroidObject;
import net.serenitybdd.screenplay.Question;

public class TheApp extends AndroidObject {
    final static String LUCKY_BALL = "com.luckynumberbouncingball";
    public static Question<Boolean> isInstalled() {
        return Question.about("the application is installed")
                .answeredBy(actor -> driver.isAppInstalled(LUCKY_BALL));
    }
}
