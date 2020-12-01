import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class Utils {
    final static String BASE_URL = "https://www.instagram.com/";
    final static String CHROME_DRIVER_LOCATION = "chromedriver";

    public static WebDriver driver = new ChromeDriver();
    public static Actions actions = new Actions(driver);
//    public static Navigate navigate = new Navigate(driver);
    public static WebDriverWait wait = new WebDriverWait(driver, 5);
    public static Random random = new Random();

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
