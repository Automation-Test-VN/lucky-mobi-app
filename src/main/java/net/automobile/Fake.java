package net.automobile;

import org.apache.commons.lang3.RandomStringUtils;

public class Fake {
    public static String username(){
        return RandomStringUtils.randomAlphabetic(8);
    }

    public static String phoneNumber(){
        return "09" + RandomStringUtils.randomNumeric(8);
    }
    public static String password(){
        return RandomStringUtils.randomAlphanumeric(3).toLowerCase()
                +RandomStringUtils.randomNumeric(4)
                +RandomStringUtils.randomAlphanumeric(3).toUpperCase();
    }
}
