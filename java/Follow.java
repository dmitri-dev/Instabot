// Page URL: https://www.instagram.com/

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class Follow extends PageObject {
    public final int followRate = 3;

    @FindAll({@FindBy(css = "div._9AhH0")})
    public List<WebElement> posts;

    @FindBy(css = "button.sqdOP.yWX7d._8A5w5")
    private WebElement likes;

    @FindAll({@FindBy(css = "button.sqdOP.L3NKy.y3zKF")})
    public List<WebElement> people;

    public Follow(WebDriver driver) {
        super(driver);
    }

    public void navigateToPost() throws InterruptedException {
        boolean isPhoto = false;
        while (!isPhoto) {
            Thread.sleep(2000);
            getPost();
            Thread.sleep(1000);
            isPhoto = isPhoto();
            if (!isPhoto) {
                Utils.actions.sendKeys
                        (Keys.ESCAPE).build().perform();
            }
            else navigateToLikes();
        }
    }

    private void navigateToLikes() {
        this.likes.click();
    }

    public void getPost() {
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> this.posts.size() >= 10
        );
        this.posts.get(
                Utils.random.nextInt(posts.size() - 1)
        ).click();
    }

    public boolean isPhoto() {
        try {
            Utils.wait.until(
                    (ExpectedCondition<Boolean>)
                            driver -> this.likes.isDisplayed()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void follow() throws InterruptedException {
        int followRate = this.followRate;
        Utils.wait.until(
                (ExpectedCondition<Boolean>)
                        driver -> !this.people.isEmpty()
        );
        Thread.sleep(1000);
        for (int i = 0; i < followRate; i++) {
            if (i >= people.size() - 1) {
                followRate -= i; i = 0;
                newPost();
            }
            people.get(i).click();
            Thread.sleep(1000);
        }
        Utils.actions.sendKeys(Keys.ESCAPE).build().perform();
    }

    private void newPost() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(2000);
        Utils.actions.sendKeys(Keys.ESCAPE).build().perform();
        Thread.sleep(1000);
        getPost();
        Thread.sleep(1000);
        navigateToLikes();
    }

//    public void verifyFollowSuccess() {
//        this.alertSuccess.isDisplayed();
//    }
}
