package com.example.instabot_test;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Selenide.*;

public class Unfollow {
    private static final String fileName = "followers.csv";
    private static final int unfollowRate = 3;

//    @FindAll({@FindBy(css = "a.-nal3")})
//    public List<WebElement> dataButtons;

    private static final ElementsCollection dataButtons = $$("a.-nal3");

//    @FindAll({@FindBy(css = "a.FPmhX.notranslate._0imsa")})
//    public List<WebElement> people;

    private static final ElementsCollection people = $$("a.FPmhX.notranslate._0imsa");

//    @FindAll({@FindBy(css = "button.sqdOP.L3NKy._8A5w5")})
//    public List<WebElement> unfollowButtons;

    private static final ElementsCollection unfollowButtons = $$("button.sqdOP.L3NKy._8A5w5");

    public static void navigateToFollowers() {
        dataButtons.get(0).click();
    }

    public static void writeFollowers() throws IOException {
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

    public static void navigateToFollowing() {
        dataButtons.get(1).click();
    }

    public static void remove() throws InterruptedException, IOException {
        List<String> following = people.stream().map(
                WebElement::getText
        ).collect(Collectors.toList());
        Map<String, WebElement> map =
        IntStream.range(0, following.size())
            .boxed().collect(
                Collectors.toMap(
                    following::get, unfollowButtons::get
                )
        );
        System.out.println("map: " + map.keySet());
        BufferedReader csvReader = new BufferedReader(
                new FileReader("followers.csv")
        );
        List<String> followers = Arrays.asList(
                csvReader.readLine().split(",")
        );
        int unfollowRate = Unfollow.unfollowRate;
        System.out.println(unfollowRate);
        for (int i = followers.size() - 3; i > 0; i--) {
            if (!followers.contains(following.get(i))) {
                Utils.randomWait(2000);
                map.get(following.get(i)).click();
                Navigate.sendKeys(new String[]{"TAB", "ENTER"});
                unfollowRate--;
            }
            if (unfollowRate <= 0) {
                break;
            }
        }
    }
}
