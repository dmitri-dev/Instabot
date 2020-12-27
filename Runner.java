package com.example.instabot_test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.example.instabot_test.Utils.*;

public class Runner {
    @BeforeClass
    public void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeMethod
    public void setUp() {
        Configuration.startMaximized = true;
        Ninja.setOptions();
        open(BASE_URL);
    }

    @Test(testName = "INZBOT")
//    public void runTest() {
//
//        int i = 0;
//        while (i < 3) {
//            login();
//            sleep(1000);
//            follow();
//            sleep(1000);
//            i++;
//        }
//    }

    public void runTest() {

        login();

//        follow();

//        unfollow();

    }


    public void login() {
        try {
            Login.enterUsername();
            randomWait(2000);
            Login.enterPassword();
            randomWait(2000);
            Login.completeLogin();
            randomWait(2000);
//            webForm.verifyAlertSuccess();
            System.out.println("Login Complete\r\n");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Login Failed\r\n");
            System.exit(0);
        }
    }

    public void follow() {
        try {
            Navigate.toExplore();
//            Utils.scrollDown();
            randomWait(2000);
            Follow.navigateToPost();
            randomWait(200);
            Follow.follow();
            randomWait(2000);
            Navigate.home();
            // Verify follow completion
            System.out.println("Following Complete\r\n");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\r\nFollow Failed");
            System.exit(0);
        }
    }

    public void unfollow() {
        try {
            Navigate.toProfile();
            randomWait(2000);
            Unfollow.navigateToFollowers();
            randomWait(2000);
            Unfollow.writeFollowers();
            randomWait(2000);
            refresh();
            randomWait(2000);
            Unfollow.navigateToFollowing();
            randomWait(2000);
            Unfollow.remove();
            //  Verify unfollow completion
            System.out.println("Unfollow Complete");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\r\nUnfollow Failed");
            System.exit(0);
        }
    }

    @AfterClass
    public void cleanUp() {
        sleep(60000);
    }
}
