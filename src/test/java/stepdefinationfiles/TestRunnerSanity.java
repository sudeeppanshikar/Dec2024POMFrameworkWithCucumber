package stepdefinationfiles;

/*import io.cucumber.junit.Cucumber;*/
/*import io.cucumber.junit.CucumberOptions;*/
import io.cucumber.testng.AbstractTestNGCucumberTests;
/*import org.junit.runner.RunWith;*/
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


/*@RunWith(Cucumber.class)*/
@CucumberOptions(
        features = "src/test/resources/parallel",
        glue = "stepdefinationfiles",
        tags = "@smoke",
        plugin = {"pretty", "html:target/cucumber-reports", "timeline:test-output-thread/",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)
public class TestRunnerSanity extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    @Parameters("browser1")
    public void setup( @Optional String browserName) {
        if(browserName!=null) {
            System.setProperty("browser", browserName);
            System.out.println("Browser set in TestContext: " + browserName);
        }
    }
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }


}
