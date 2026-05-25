package runner;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "hooks"},
    tags = "@SingupFlow",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-html-report.html",
        "json:target/cucumber-reports/cucumber.json"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    public TestRunner() {
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        
        // Dynamically overrides the HTML output target path with a distinct non-overwriting file layout
        System.setProperty("cucumber.plugin", "html:target/cucumber-reports/report_" + timestamp + ".html");
        System.setProperty("cucumber.publish.quiet", "true");
        
        System.out.println("=========================================================================");
        System.out.println("🚀 FRAMEWORK RUNNER CONTROLLER INITIALIZED");
        System.out.println("🎯 Active Target Tag    : @SignupFlow");
        System.out.println("📊 Non-Overwriting Path : target/cucumber-reports/report_" + timestamp + ".html");
        System.out.println("=========================================================================");
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}