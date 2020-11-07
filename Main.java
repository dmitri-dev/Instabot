package com.example.instagram_test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

public class MainPageTest {
    WebDriver driver = setDriver();
    Actions actions = new Actions(driver);
    Random rand = new Random();
    int setAttempts = 3;
    int followRate = 10;
    String username = "sudokevin";
    String password = "jhunter";

    WebDriver setDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Programs/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }

    void navigate(String[] keys) {
        for (String key : keys) {
            actions.sendKeys(Keys.valueOf(key)).build().perform();
        }
    }

    List<WebElement> getPosts(int attempts) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> posts = driver.findElements(By.cssSelector("div._9AhH0"));
        if (!posts.isEmpty()) {
            return posts;
        } else if (attempts > 0) {
            getPosts(--attempts);
        }
        return posts;
    }

    void getPost(List<WebElement> posts) throws InterruptedException {
        Thread.sleep(1000);
        int random = rand.nextInt((posts.size()) - 1);
        posts.get(random).click();
        Thread.sleep(1000);
        boolean isPhoto = !driver.findElements(By.cssSelector("button.sqdOP.yWX7d._8A5w5")).isEmpty();
        if (!isPhoto) {
            System.out.println("Invalid Post");
            actions.sendKeys(Keys.ESCAPE).build().perform();
            getPost(posts);
        }
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button.sqdOP.yWX7d._8A5w5")).click();
    }

    void getFollowers(List<WebElement> people, int attempts) throws InterruptedException {
        Thread.sleep(1000);
        if (people.size() > followRate) {
            follow(people, followRate);
        } else if (attempts > 0) {
            driver.navigate().refresh();
            getFollowers(people, --attempts);
        }
    }

    void follow(List<WebElement> people, int followRate) throws InterruptedException {
        Thread.sleep(1000);
        if (followRate > 0) {
            people.get(people.size() - followRate).click();
            follow(people, --followRate);
        }
    }

    @Test
    public void run() throws InterruptedException {
        driver.get("https://www.instagram.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        actions.sendKeys(Keys.chord(Keys.ALT, "d")).build().perform();
        // LOGIN
        driver.findElement(By.xpath("//input[@aria-label='Phone number, username, or email']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@aria-label='Password']")).sendKeys(password);
        Thread.sleep(2000);
        navigate(new String[] {"TAB", "TAB", "ENTER"});
        actions.sendKeys(Keys.TAB).build().perform();
        navigate(new String[] {"TAB", "ENTER"});
        Thread.sleep(2000);
        navigate(new String[] {"TAB", "ENTER"});
        Thread.sleep(2000);
        actions.sendKeys(Keys.TAB).build().perform();
        navigate(new String[] {"TAB", "ENTER"});
        Thread.sleep(1000);

        // EXPLORE PAGE
        driver.findElement(By.cssSelector("div.eyXLr.wUAXj")).click();
        for (int i = 0; i < 3; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }
        navigate(new String[] {"TAB", "ENTER"});
        Thread.sleep(1000);
        //-----------------------//
        /*
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
        Thread.sleep(1000);
        */
        //-----------------------//
        List<WebElement> posts = getPosts(setAttempts);
        //-----------------------//
//        System.out.println(posts);
        //-----------------------//

        // POST
        getPost(posts);
        Thread.sleep(2000);

        // LIKES
        List<WebElement> people = driver.findElements(By.cssSelector("button.sqdOP.L3NKy.y3zKF"));
        getFollowers(people, setAttempts);
        System.out.println(people);
    }
}
