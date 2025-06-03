package stepdefinationfiles;

/*import io.cucumber.junit.Cucumber;*/
/*import io.cucumber.junit.CucumberOptions;*/

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


/*@RunWith(Cucumber.class)*/
@CucumberOptions(
        features = "src/test/resources/parallel",
        glue = "stepdefinationfiles",
        tags = "@regression",
        plugin = {"pretty", "html:target/cucumber-reports", "timeline:test-output-thread/",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)
public class TestRunnerRegression extends AbstractTestNGCucumberTests {


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
