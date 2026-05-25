package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonMethods;

public class LoginPage {
    private WebDriver driver;
    private CommonMethods cm;

    // =========================================================================
    // LOCATORS: LOGIN & NAVIGATION COMPONENTS
    // =========================================================================
    private final By emailField = By.xpath("//input[@id='email']");
    private final By mailButton = By.xpath("//button[@id='emailBtn']");
    private final By loginJoinButton = By.xpath("(//a[contains(normalize-space(),'Join / Login')])[1]");
    private final By passwordLoginField = By.xpath("//input[@id='passwordLogin']");
    private final By loginButton = By.xpath("//button//span[contains(.,'Login')]");
    private final By threeDotsMenu = By.xpath("//i[contains(@class,'icon-more_vert2')]/parent::button");
    private final By logoutButton = By.xpath("//a[contains(text(),'Logout')]");

    // =========================================================================
    // LOCATORS: SIGNUP & WIZARD COMPONENTS (Tokenized XPaths)
    // =========================================================================
    private final By signUpButton = By.xpath("//span[text()='Sign Up']/parent::button");
    private final By otpField = By.xpath("//input[@id='otp_input']");
    private final By verifyButton = By.xpath("//span[text()='Verify']/parent::button");
    private final By joinAlumniButton = By.xpath("//span[contains(text(),'Join Alumni Network')]/parent::button");
    private final By roleDropdown = By.xpath("//select[@name='role']");
    private final By yojDropdown = By.xpath("//select[@name='yoj']");
    private final By yopDropdown = By.xpath("//select[@name='yop']");
    
    // Locators: Basic Details & Profile Picture Setup
    private final By countryCodeDropdown = By.xpath("//select");
    private final By phoneField = By.xpath("//input[contains(@name,'phone') or contains(@placeholder,'Phone')]");
    private final By nextStepButton = By.xpath("//span[contains(text(),'Next step')]/parent::button");
    private final By fileUploadInput = By.xpath("//input[@type='file']");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");
    private final By saveChangesButton = By.xpath("//button[contains(.,'Save changes')]");
    private final By goToProfileButton = By.xpath("//button[contains(.,'Go to your Profile')]");

    // Dynamic XPath Templates using %s token instead of concatenation
    private final String signupInputXpathTemplate = "//label[contains(text(),'%s')]/preceding-sibling::input";
    private final String checkboxXpathTemplate = "//label[contains(., '%s')]//span[@class='mdl-checkbox__box-outline']";
    private final String citySuggestionXpathTemplate = "(//ul[contains(@class,'paAutosuggestionsUl')]//span[contains(text(),'%s')])[1]";

    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.cm = new CommonMethods(driver);
    }

    // =========================================================================
    // ACTIONS: LOGIN FLOWS
    // =========================================================================
    public void enterEmail(String email) {
        cm.scrollIntoView(emailField);
        cm.type(emailField, email);
    }

    public void clickMailButton() {
        cm.scrollIntoView(mailButton);
        cm.click(mailButton);
    }

    public void clickJoinLoginButton() {
        cm.scrollIntoView(loginJoinButton);
        cm.click(loginJoinButton);
        cm.waitForVisibility(emailField);
    }

    public void enterLoginPassword(String password) {
        cm.scrollIntoView(passwordLoginField);
        cm.type(passwordLoginField, password);
    }

    public void clickLoginButton() {
        cm.scrollIntoView(loginButton);
        cm.click(loginButton);
    }

    public void clickThreeDotsMenu() {
        cm.scrollIntoView(threeDotsMenu);
        cm.click(threeDotsMenu);
    }

    public void clickLogoutButton() {
        cm.scrollIntoView(logoutButton);
        cm.click(logoutButton);
    }

    // =========================================================================
    // ACTIONS: SIGNUP STEP FLOWS
    // =========================================================================
    public void clickSignUpButton() {
        cm.scrollIntoView(signUpButton);
        cm.click(signUpButton);
    }

    public void enterOTP(String otp) {
        cm.scrollIntoView(otpField);
        cm.type(otpField, otp);
    }

    public void clickVerifyButton() {
        cm.scrollIntoView(verifyButton);
        cm.click(verifyButton);
    }

    public void enterSignupInput(String key, String value) {
        String formattedXpath = String.format(signupInputXpathTemplate, key);
        By dynamicField = By.xpath(formattedXpath);
        cm.scrollIntoView(dynamicField);
        cm.type(dynamicField, value);
    }

    public void selectRole(String role) {
        cm.scrollIntoView(roleDropdown);
        new Select(cm.waitForVisibility(roleDropdown)).selectByVisibleText(role);
    }

    public void selectJoiningYear(String yoj) {
        cm.scrollIntoView(yojDropdown);
        new Select(cm.waitForVisibility(yojDropdown)).selectByVisibleText(yoj);
    }

    public void selectGraduationYear(String yop) {
        cm.scrollIntoView(yopDropdown);
        new Select(cm.waitForVisibility(yopDropdown)).selectByVisibleText(yop);
    }

    public void clickCheckbox(String checkboxValue) {
        String formattedXpath = String.format(checkboxXpathTemplate, checkboxValue);
        By checkboxLabel = By.xpath(formattedXpath);
        cm.scrollIntoView(checkboxLabel);
        try {
            cm.click(checkboxLabel);
        } catch (Exception e) {
            System.out.println("Standard click intercepted on checkbox. Using JS click fallback.");
            cm.jsClick(checkboxLabel);
        }
    }

    public void clickJoinAlumniNetwork() {
        cm.scrollIntoView(joinAlumniButton);
        cm.click(joinAlumniButton);
    }

    public void enterCityDetails(String cityName) {
        try {
            By cityField = By.xpath("//label[contains(text(),'Current City')]/preceding-sibling::input");
            cm.scrollIntoView(cityField);
            cm.click(cityField);
            cm.type(cityField, cityName);
            
            String formattedXpath = String.format(citySuggestionXpathTemplate, cityName);
            By citySuggestion = By.xpath(formattedXpath);
            cm.click(citySuggestion);
        } catch (Exception e) {
            System.out.println("City autocomplete suggestion interaction failed: " + e.getMessage());
        }
    }

    public void selectCountryCode(String countryCode) {
        try {
            cm.scrollIntoView(countryCodeDropdown);
            new Select(cm.waitForVisibility(countryCodeDropdown)).selectByVisibleText(countryCode);
        } catch (Exception e) {
            System.out.println("Country selection field omitted: " + e.getMessage());
        }
    }

    public void enterPhoneNumber(String phone) {
        try {
            cm.scrollIntoView(phoneField);
            cm.type(phoneField, phone);
        } catch (Exception e) {
            System.out.println("Phone interaction field omitted: " + e.getMessage());
        }
    }

    public void clickNextStep() {
        cm.scrollIntoView(nextStepButton);
        cm.click(nextStepButton);
    }

    public void uploadProfilePhoto(String filePath) {
        cm.waitForPresence(fileUploadInput).sendKeys(filePath);
    }

    public void clickSaveButton() {
        cm.scrollIntoView(saveButton);
        cm.waitForClickable(saveButton);
        cm.jsClick(saveButton);
    }
    
    public void clickSaveChangesButton() throws InterruptedException {
    	cm.waitForClickable(saveChangesButton);
        cm.moveAndClick(saveChangesButton);
    }

    public void clickGoToProfileButton() {
        cm.scrollIntoView(goToProfileButton);
        cm.waitForClickable(goToProfileButton);
        cm.jsClick(goToProfileButton);
    }
}