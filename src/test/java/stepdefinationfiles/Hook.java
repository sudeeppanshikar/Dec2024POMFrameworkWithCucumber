package stepdefinationfiles;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.util.Properties;

public class Hook {


    DriverFactory df;
    protected LoginPage loginpage;
    protected AccountPage accountpage;
    protected SearchResultsPage searchrespage;
    protected ProductInfoPage prodinfopage;
    protected RegisterPage registerpage;
    protected CartInfoPage cartinfopage;
    WebDriver driver;
    protected Properties prop;

    /*  @Step("Intializing the Driver through Driver Factory")*/


    @Before
    public void setup() {

        System.out.println("inside setup");
        df = new DriverFactory();
        prop = df.initprop();

        driver = df.initBrowser(prop);
        df.launchURL(prop);
        loginpage = new LoginPage(driver);

    }


    @After
    // Text attachments for Allure
    public void attachScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            // Attach screenshot to Allure
                Allure.addAttachment("Failed Screenshot",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }

    }


    @After
    public void teardown() {
        df.driverQuit();
    }

    public LoginPage getloginpage() {
        return loginpage;
    }

    public AccountPage getaccountpage() {
        return accountpage;
    }

    public WebDriver getwebdriver() {
        return driver;
    }

    public Properties getProp() {
        return prop;
    }

}
