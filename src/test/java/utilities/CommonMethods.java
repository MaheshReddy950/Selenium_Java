package utilities;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

    WebDriver driver;

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }

    // ==========================================
    // WAIT FOR VISIBILITY
    // ==========================================
    public WebElement waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
 // ==========================================
    // WAIT FOR CLICKABLE (Ensures element is ready for interaction)
    // ==========================================
    public WebElement waitForClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ==========================================
    // WAIT FOR PRESENCE (Fixes your upload error)
    // ==========================================
    public WebElement waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ==========================================
    // CLICK
    // ==========================================
    public void click(By locator) {
        waitForVisibility(locator).click();
    }

    // ==========================================
    // TYPE
    // ==========================================
    public void type(By locator, String value) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(value);
    }

    // ==========================================
    // JS CLICK
    // ==========================================
    public void jsClick(By locator) {
        WebElement element = waitForVisibility(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    
    public void moveAndClick(By locator) throws InterruptedException {
        WebElement element = waitForClickable(locator);
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        Thread.sleep(6000);
        actions.moveToElement(element)
               .click()
               .build()
               .perform();
        System.out.println("🖱️ Mouse moved cleanly to element and click executed.");
    }

    // ==========================================
    // SCROLL
    // ==========================================
    public void scrollIntoView(By locator) {
        WebElement element = waitForVisibility(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // ==========================================
    // DYNAMIC EMAIL
    // ==========================================
    public String generateDynamicEmail(String email) {
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];
        long timestamp = System.currentTimeMillis();
        return username + "+" + timestamp + "@" + domain;
    }

    // ==========================================
    // DYNAMIC NAME
    // ==========================================
    public String generateDynamicName(String name) {
        int random = (int)(Math.random() * 1000);
        return name + random;
    }

    // ==========================================
    // DYNAMIC PHONE NUMBER
    // ==========================================
    public String generateDynamicPhoneNumber(String phoneNumber) {
        long random = (long)(Math.random() * 9000000000L) + 1000000000L;
        return String.valueOf(random);
    }

    // ==========================================
    // FETCH OTP FROM YOPMAIL
    // ==========================================
    public String fetchOTPFromYopmail(String generatedEmail) throws Exception {
        String mainWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://yopmail.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String inboxName = generatedEmail.replace("@yopmail.com", "");

        WebElement inboxField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        inboxField.clear();
        inboxField.sendKeys(inboxName);

        driver.findElement(By.xpath("//button[@title='Check Inbox @yopmail.com']")).click();

        for(int i = 1; i <= 2; i++) {
            driver.switchTo().defaultContent();
            WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("refresh")));
            refreshButton.click();
            Thread.sleep(3000);
        }

        driver.switchTo().frame("ifinbox");
        List<WebElement> mails = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='m']")));
        mails.get(0).click();

        driver.switchTo().defaultContent();
        driver.switchTo().frame("ifmail");

        WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        String mailText = body.getText();

        Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
        Matcher matcher = pattern.matcher(mailText);
        String otp = "";

        if(matcher.find()) {
            otp = matcher.group();
        }

        driver.close();
        driver.switchTo().window(mainWindow);
        driver.switchTo().defaultContent();

        return otp.trim();
    }
    
 // =========================================================================
    // NATIVE CUCUMBER IN-LINE ATTACHMENT HELPER
    // =========================================================================
    public void attachScreenshotToStep(io.cucumber.java.Scenario scenario, String stepLabel) {
        if (driver != null) {
            try {
                byte[] screenshotBytes = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                // Attaches the image exactly inside the step definition scope
                scenario.attach(screenshotBytes, "image/png", stepLabel);
            } catch (Exception e) {
                System.out.println("Could not embed runtime step screenshot: " + e.getMessage());
            }
        }
    }
}