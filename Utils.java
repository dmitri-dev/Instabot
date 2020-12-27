package com.example.instabot_test;

import com.codeborne.selenide.Selenide;

import java.util.Random;

public class Utils {
    final static String BASE_URL = "https://www.instagram.com/";
    final static String CHROME_DRIVER_LOCATION = "chromedriver";

    public static Random random = new Random();

    public static void randomWait(long timeOut) {
        long randomInt = (long) (random.nextInt(
                (int) (timeOut * 1.25 - timeOut * 0.75)
        ) + timeOut * 0.75);
        System.out.println(randomInt);
        Selenide.sleep(
                randomInt
        );
    }

    /*
    public static void scrollDown() {
        //-----------------------//
        *//*
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
        Thread.sleep(1000);
        *//*
        //-----------------------//
    }
    */
}
