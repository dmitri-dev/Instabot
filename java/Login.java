// Page URL: https://www.instagram.com/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class Login extends PageObject {
    private final String USERNAME = "8455871152";
    private final String PASSWORD = "Password1!";
    // Make Navigator static
    private final Navigate Navigate = new Navigate(this.driver);

    @FindBy(xpath = "//input[@aria-label='Phone number, username, or email']")
    private WebElement username;

    @FindBy(xpath = "//input[@aria-label='Password']")
    private WebElement password;

//    @FindBy(xpath = "//div[contains(text(), 'The form was successfully submitted!')]")
//    private WebElement alertSuccess;

    public Login(WebDriver driver) {
        super(driver);
    }

    public void enterUsername() {
        this.username.sendKeys(USERNAME);
    }

    public void enterPassword() {
        this.password.sendKeys(PASSWORD);
    }

    public void completeLogin() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            Navigate.sendKeys(new String[]{"TAB", "TAB", "ENTER"});
            Thread.sleep(1000);
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