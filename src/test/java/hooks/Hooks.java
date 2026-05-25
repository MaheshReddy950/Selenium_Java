package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseTest {

    // Global static reference shared across step definition instances
    public static Scenario currentScenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("====== Starting Scenario Execution ======");
        // Automatically captures the active scenario context right as the test boots up
        currentScenario = scenario;
        launchBrowser(); 
    }

    @After
    public void afterScenario() {
        System.out.println("====== Ending Scenario Execution ======");
        closeBrowser();
    }
}