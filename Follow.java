package com.example.instabot_test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.codeborne.selenide.Selenide.*;
import static com.example.instabot_test.Utils.*;

public class Follow {
    public static final int followRate = 1;

//    @FindAll({@FindBy(css = "div._9AhH0")})
//    public static List<WebElement> posts;

    public static ElementsCollection posts = $$("div._9AhH0");

//    @FindBy(css = "button.sqdOP.yWX7d._8A5w5")
//    private static WebElement likes;

    public static SelenideElement likes = $("button.sqdOP.yWX7d._8A5w5");

//    @FindAll({@FindBy(css = "button.sqdOP.L3NKy.y3zKF")})
//    public static List<WebElement> people;

    public static ElementsCollection people = $$("button.sqdOP.L3NKy.y3zKF");

    public static void navigateToPost() {
        boolean isPhoto = false;
        while (!isPhoto) {
            randomWait(2000);
            getPost();
            randomWait(1500);
            isPhoto = isPhoto();
            if (!isPhoto) {
                actions().sendKeys
                        (Keys.ESCAPE).build().perform();
            }
            else navigateToLikes();
        }
    }

    private static void navigateToLikes() {
        likes.click();
    }

    public static void getPost() {
        Wait().until(
                (ExpectedCondition<Boolean>)
                        driver -> posts.size() >= 10
        );
        posts.get(
                Utils.random.nextInt(posts.size() - 1)
        ).click();
    }

    public static boolean isPhoto() {
        try {
            Wait().until(
                    (ExpectedCondition<Boolean>)
                            driver -> likes.isDisplayed()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void follow() {
        int followRate = Follow.followRate;
        Wait().until(
                (ExpectedCondition<Boolean>)
                        driver -> !people.isEmpty()
        );
        randomWait(1500);
        for (int i = 0; i < followRate; i++) {
            if (i >= people.size() - 1) {
                followRate -= i; i = 0;
                newPost();
            }
            people.get(i).click();
            randomWait(1500);
        }
        actions().sendKeys(Keys.ESCAPE).build().perform();
    }

    private static void newPost() {
        refresh();
        randomWait(2000);
        actions().sendKeys(Keys.ESCAPE).build().perform();
        randomWait(1000);
        getPost();
        randomWait(1000);
        navigateToLikes();
    }

//    public void verifyFollowSuccess() {
//        this.alertSuccess.isDisplayed();
//    }
}
