package com.example.instabot_test;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.example.instabot_test.Utils.*;

public class Navigate {
    private static final SelenideElement homeButton = $(".oJZym");
    private static final SelenideElement search = $("div.eyXLr.wUAXj");
    private static final SelenideElement profile = $("span._2dbep.qNELH");

    public static void sendKeys(String[] keys) {
        for (String key : keys) {
            randomWait(1500);
            actions().sendKeys(Keys.valueOf(key)).build().perform();
        }
    }

    public static void home() {
        homeButton.click();
    }

    public static void toSearch() {
        search.click();
    }

    public static void toExplore() {
        toSearch();
        sendKeys(new String[]{
                "TAB", "TAB", "TAB", "TAB", "ENTER"
        });
    }

    public static void toProfile() {
        profile.click();
        sendKeys(new String[]{"TAB", "ENTER"});
    }
}