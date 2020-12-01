import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
public class Navigate extends PageObject {
    public Navigate(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "oJZym")
    private static WebElement homeButton;

    @FindBy(css = "div.eyXLr.wUAXj")
    private static WebElement search;

    public static void sendKeys(String[] keys) throws InterruptedException {
        for (String key : keys) {
            Thread.sleep(1000);
            Utils.actions.sendKeys(Keys.valueOf(key)).build().perform();
        }
    }

    public static void home() {
        homeButton.click();
    }

    public static void toSearch() {
        search.click();
    }

    public static void toExplore() throws InterruptedException {
        toSearch();
        sendKeys(new String[]{
                "TAB", "TAB", "TAB", "TAB", "ENTER"
        });
    }
}
*/

public class Navigate extends PageObject {
    @FindBy(className = "oJZym")
    private WebElement homeButton;

    @FindBy(css = "div.eyXLr.wUAXj")
    private WebElement search;

    @FindBy(css = "span._2dbep.qNELH")
    private WebElement profile;

    public Navigate(WebDriver driver) {
        super(driver);
    }

    public void sendKeys(String[] keys) throws InterruptedException {
        for (String key : keys) {
            Thread.sleep(1000);
            Utils.actions.sendKeys(Keys.valueOf(key)).build().perform();
        }
    }

    public void home() {
         this.homeButton.click();
    }

    public void toSearch() {
        this.search.click();
    }

    public void toExplore() throws InterruptedException {
        this.toSearch();
        this.sendKeys(new String[]{
                "TAB", "TAB", "TAB", "TAB", "ENTER"
        });
    }

    public void toProfile() throws InterruptedException {
        this.profile.click();
        this.sendKeys(new String[]{"TAB", "ENTER"});
    }
}