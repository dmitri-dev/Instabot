import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Unfollow extends PageObject {
    private static final String fileName = "followers.csv";
    private static final int unfollowRate = 3;

    // Make Navigator static
    private final Navigate Navigate = new Navigate(this.driver);

    @FindAll({@FindBy(css = "a.-nal3")})
    public List<WebElement> dataButtons;

    @FindAll({@FindBy(css = "a.FPmhX.notranslate._0imsa")})
    public List<WebElement> people;

    @FindAll({@FindBy(css = "button.sqdOP.L3NKy._8A5w5")})
    public List<WebElement> unfollowButtons;

    public Unfollow(WebDriver driver) {
        super(driver);
    }

    public void navigateToFollowers() {
        this.dataButtons.get(0).click();
    }

    public void writeFollowers() throws IOException {
        List<String> followers = people.stream().map(
                WebElement::getText
        ).collect(Collectors.toList());
        System.out.println("followers: " + followers);
        System.out.println("followers: " + followers.size());
        File csvFile = new File(fileName);
        if (!csvFile.isFile()) {
            FileWriter csvWriter = new FileWriter(fileName);
            for (String follower : followers) {
                csvWriter.append(follower).append(",");
            }
            csvWriter.flush();
            csvWriter.close();
        } else {
            FileWriter csvWriter = new FileWriter(fileName, true);
            BufferedReader csvReader = new BufferedReader(
                    new FileReader("followers.csv")
            );
            List<String> data = Arrays.asList(
                    csvReader.readLine().split(",")
            );
            for (String follower : followers) {
                if (!data.contains(follower)) {
                    csvWriter.append(follower).append(",");
                }
            }
            csvWriter.flush();
            csvWriter.close();
            csvReader.close();
        }
    }

    public void navigateToFollowing() {
        this.dataButtons.get(1).click();
    }

    public void remove() throws InterruptedException, IOException {
        List<String> following = people.stream().map(
                WebElement::getText
        ).collect(Collectors.toList());
        Map<String, WebElement> map =
        IntStream.range(0, following.size())
            .boxed().collect(
                Collectors.toMap(
                    following::get, i ->
                        unfollowButtons.get(i)
                )
        );

        System.out.println("map: " + map);

        BufferedReader csvReader = new BufferedReader(
                new FileReader("followers.csv")
        );
        List<String> followers = Arrays.asList(
                csvReader.readLine().split(",")
        );
        for (int i = 0; i < unfollowRate; i++) {
            if (!followers.contains(following.get(i))) {
                Thread.sleep(2000);
                map.get(following.get(i)).click();
                Navigate.sendKeys(new String[]{"TAB", "ENTER"});
            }
        }
    }

    public void readFollowers() throws IOException {
        File csvFile = new File("followers.csv");
        if (csvFile.isFile()) {
            BufferedReader csvReader = new BufferedReader(
                    new FileReader("followers.csv")
            );
            String[] followers = csvReader.readLine().split(",");

            for (String follower : followers) {
                System.out.println(follower);
            }

            csvReader.close();
        }
    }

    public void printFollowers() {
        try {
            System.out.println("text: " + people.get(0).getText());
            System.out.println("name: " + people.get(0).getAttribute("name"));
            System.out.println("location: " + people.get(0).getLocation());
            System.out.println("rect: " + people.get(0).getRect());
            System.out.println("class: " + people.get(0).getClass());
            System.out.println("size: " + people.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
