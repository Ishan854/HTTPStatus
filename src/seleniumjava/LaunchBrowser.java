package seleniumjava;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Response;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowser {

    public static void main(String[] args) {
    	WebDriverManager.chromedriver().setup();

//        System.setProperty("webdriver.chrome.driver", "F:\\SeleniumJava\\SeleniumCourse\\Resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://telugu.timesnownews.com/");
        String currentURL = driver.getCurrentUrl();
        System.out.println("Current URL = " + currentURL);
        String pageSource = driver.getPageSource();
        System.out.println("Page Source = " + pageSource);
        String title = driver.getTitle();
        System.out.println("Title =  " + title);
        List<WebElement> total_Links = driver.findElements(By.tagName("link"));
        int count = total_Links.size();
        System.out.println("Number of links available on this Website: " + count);
        List<String> linkList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String link = total_Links.get(i).getAttribute("href");
            linkList.add(link);
        }
        for (String link : linkList) {
            System.out.println(link);
        }
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.addListener(Network.responseReceived(),
                response -> {
                    Response res = response.getResponse();
                    System.out.println(res.getHeaders());
                    System.out.println(res.getUrl());
                    System.out.println(res.getStatus());
                });
    }
}