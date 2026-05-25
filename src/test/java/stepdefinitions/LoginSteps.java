package stepdefinitions;

import base.BaseTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.Map;
import Pages.LoginPage;
import utilities.CommonMethods;
import hooks.Hooks;

public class LoginSteps extends BaseTest {
    
    private LoginPage loginPage;
    private CommonMethods cm;

    public static String generatedEmail;
    public static String generatedPassword;
    public static String generatedFirstName;
    public static String generatedLastName;
    public static String generatedPhoneNumber;

    // =========================================================================
    // CONSTRUCTOR: Pre-initializes page objects directly using inherited driver
    // =========================================================================
    public LoginSteps() {
        this.loginPage = new LoginPage(driver);
        this.cm = new CommonMethods(driver);
    }

    @Given("User launches chrome browser")
    public void user_launches_chrome_browser() {
        cm.attachScreenshotToStep(Hooks.currentScenario, "Browser Launched");
    }

    @Then("User closes browser")
    public void user_closes_browser() {
        cm.attachScreenshotToStep(Hooks.currentScenario, "Before Browser Close");
        closeBrowser();
    }

    @Then("User enters email address")
    public void user_enters_email_address(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String baseEmail = data.get("Email");

        if (baseEmail == null || baseEmail.trim().isEmpty()) {
            loginPage.enterEmail(generatedEmail);
            System.out.println("Logged in with Email: " + generatedEmail);
        } else {
            generatedEmail = cm.generateDynamicEmail(baseEmail);
            System.out.println("Generated Signup Email: " + generatedEmail);
            loginPage.enterEmail(generatedEmail);
        }
        cm.attachScreenshotToStep(Hooks.currentScenario, "Entered Email Address");
    }

    @Then("Click on Mail Button")
    public void click_on_mail_button() {
        loginPage.clickMailButton();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Mail Button");
    }

    @Then("Click on Signup Button")
    public void click_on_signup_button() {
        loginPage.clickSignUpButton();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Signup Button");
    }

    @Then("User enters OTP")
    public void user_enters_otp() throws Exception {
        String otp = cm.fetchOTPFromYopmail(generatedEmail);
        System.out.println("Fetched OTP: " + otp);
        driver.switchTo().defaultContent();
        loginPage.enterOTP(otp);
        cm.attachScreenshotToStep(Hooks.currentScenario, "Entered OTP Code");
    }

    @Then("Click on Verify Button")
    public void click_on_verify_button() {
        loginPage.clickVerifyButton();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Verify Button");
    }

    @Then("User enters Password address")
    public void user_enters_password_address() {
        loginPage.enterLoginPassword(generatedPassword);
        System.out.println("Logged in with Password: " + generatedPassword);
        cm.attachScreenshotToStep(Hooks.currentScenario, "Entered Login Password");
    }

    @Then("Click on Login Button")
    public void click_on_login_button() {
        loginPage.clickLoginButton();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Login Button");
    }

    @Then("User clicks on Three Dots Menu")
    public void user_clicks_on_three_dots_menu() {
        loginPage.clickThreeDotsMenu();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Three Dots Menu");
    }

    @Then("User clicks on Logout Button")
    public void user_clicks_on_logout_button() {
        loginPage.clickLogoutButton();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Logout Button");
    }

    @Then("Click on join button")
    public void click_on_login_join_button() {
        loginPage.clickJoinLoginButton();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Join Button");
    }

    @Then("Click on Join Alumni Network Button")
    public void click_on_join_alumni_network_button() {
        loginPage.clickJoinAlumniNetwork();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Join Alumni Network Button");
    }

    @Then("Enter Basic Details")
    public void enter_basic_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        
        if (data.get("City") != null) {
            loginPage.enterCityDetails(data.get("City"));
        }
        if (data.get("Country Code") != null) {
            loginPage.selectCountryCode(data.get("Country Code"));
        }
        if (data.get("Phone Num") != null) {
            generatedPhoneNumber = cm.generateDynamicPhoneNumber(data.get("Phone Num"));
            loginPage.enterPhoneNumber(generatedPhoneNumber);
        }
        cm.attachScreenshotToStep(Hooks.currentScenario, "Basic Details Completed Form State");
    }

    @Then("Add Profile Photo")
    public void add_profile_photo(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        loginPage.uploadProfilePhoto(data.get("Path"));
        cm.attachScreenshotToStep(Hooks.currentScenario, "Uploaded Profile Photo");
    }

    @Then("Click on Next Step Button")
    public void click_on_next_step_button() {
        loginPage.clickNextStep();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Next Step Button");
    }

    @Then("Click on Save Button")
    public void click_on_save_button() {
        loginPage.clickSaveButton();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Save Button");
    }

    @Then("Click on Save Changes Button")
    public void click_on_save_changes_button() throws Exception {
        loginPage.clickSaveChangesButton();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Save Changes Button");
    }

    @Then("Click on Go To Your Profile Button")
    public void click_on_go_to_your_profile_button() throws Exception {
        loginPage.clickGoToProfileButton();
        cm.attachScreenshotToStep(Hooks.currentScenario, "Clicked Go To Profile Button");
    }

    @Then("Enters signup details")
    public void enters_signup_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.equalsIgnoreCase("First Name")) {
                generatedFirstName = cm.generateDynamicName(value);
                value = generatedFirstName;
            } else if (key.equalsIgnoreCase("Last Name")) {
                generatedLastName = cm.generateDynamicName(value);
                value = generatedLastName;
            } else if (key.equalsIgnoreCase("Password")) {
                generatedPassword = value;
            }
            loginPage.enterSignupInput(key, value);
        }
        cm.attachScreenshotToStep(Hooks.currentScenario, "Signup Details Fields Entry Form State");
    }

    @Then("Enter role details")
    public void enter_role_details(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            switch (key) {
                case "Role":
                    loginPage.selectRole(value);
                    break;
                case "Joining Year":
                    loginPage.selectJoiningYear(value);
                    break;
                case "Graduation Year":
                    loginPage.selectGraduationYear(value);
                    break;
                default:
                    if (key.contains("Checkbox")) {
                        loginPage.clickCheckbox(value);
                    }
                    break;
            }
        }
        cm.attachScreenshotToStep(Hooks.currentScenario, "Role Details Selection Form State");
    }
}