import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Runner {
    protected static final WebDriver driver = Utils.driver;
    // Make Navigator static
    private static final Navigate Navigate = new Navigate(driver);

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Inzbot")
    public static void runTest() throws InterruptedException {
        driver.get(Utils.BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(2000);

//        Utils.actions.sendKeys(Keys.chord(Keys.ALT, "d")).build().perform();

        login();
        Thread.sleep(2000);

//        follow();

        Thread.sleep(2000);

        unfollow();

    }

    public static void login() {
        try {
            Login login = new Login(driver);
            login.enterUsername();
            Thread.sleep(1000);
            login.enterPassword();
            Thread.sleep(1000);
            login.completeLogin();
            Thread.sleep(1000);
//            Add login success assertion
//            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//            webForm.verifyAlertSuccess();
            System.out.println("Login Complete\r\n");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Login Failed\r\n");
            System.exit(0);
        }
    }

    public static void follow() {
        try {
            Follow follow = new Follow(driver);
            Navigate.toExplore();
//            Thread.sleep(1000);
//            Utils.scrollDown();
//            Thread.sleep(1000);
            Thread.sleep(1000);
            follow.navigateToPost();
            Thread.sleep(1000);
            follow.follow();
            Thread.sleep(1000);
            Navigate.home();
//            Verify follow completion
            System.out.println("Following Complete\r\n");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\r\nFollow Failed");
            System.exit(0);
        }
    }

    public static void unfollow() {
        try {
            Unfollow unfollow = new Unfollow(driver);
            Navigate.toProfile();
            Thread.sleep(2000);
            unfollow.navigateToFollowers();
            Thread.sleep(2000);
            unfollow.writeFollowers();
            Thread.sleep(2000);
            driver.navigate().refresh();
            Thread.sleep(2000);
            unfollow.navigateToFollowing();
            Thread.sleep(2000);
            unfollow.remove();
            Thread.sleep(2000);

            Thread.sleep(1000000);

//            Verify unfollow completion
            System.out.println("Unfollow Complete");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\r\nUnfollow Failed");
            System.exit(0);
        }
    }

    /*
    public static void LikePhotos() {
        try {

        } catch (Exception e) {
            System.out.println(e.getMessage() + "\nLikes Failed\n");
            System.exit(0);
        }
    }
    */

    @AfterSuite
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
//        driver.close();
    }
}