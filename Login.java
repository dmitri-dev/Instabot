package com.example.instabot_test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.example.instabot_test.Utils.*;
import static com.example.instabot_test.Navigate.*;

public class Login {
    private static final String USERNAME = "senior_roboto";
    private static final String PASSWORD = "Password1!";

    public static SelenideElement username = $x("//input[@aria-label='Phone number, username, or email']");
    public static SelenideElement password = $x("//input[@aria-label='Password']");

    public static void enterUsername() {
        username.sendKeys(USERNAME);
    }

    public static void enterPassword() {
        password.sendKeys(PASSWORD);
    }

    public static void completeLogin() {
        for (int i = 0; i < 3; i++) {
            randomWait(1500);
            sendKeys(new String[]{"TAB", "TAB", "ENTER"});
            randomWait(1500);
        }
    }

    /*
    public void verifyLoginSuccess() {
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> Follow.size() >= 10
        );
        this.alertSuccess.isDisplayed();
    }
    */
}