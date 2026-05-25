package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public static WebDriver driver;

    public void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.almashines.com/dtc/account");
        System.out.println("Browser launched successfully");
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Browser closed successfully");
    }
}