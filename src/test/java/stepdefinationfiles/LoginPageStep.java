package stepdefinationfiles;

import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPageStep {

    LoginPage loginPage;
    AccountPage accountpage;
    String acctpage_tittle;
    boolean buttonexist;


    public LoginPageStep(Hook hook) {
        this.loginPage = hook.getloginpage();

    }


    @Given("User is on the login page")
    public void userIsOnTheLoginPage() {

        String url = loginPage.getLoginPageURL();
        Assert.assertTrue(url.contains(LOGIN_PAGE_FRACTIONAL_URL));


    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTittle) {

        String actualTittle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTittle, expectedTittle);

    }

    @Given("the user is on the login page which contains {string}")
    public void theUserIsOnTheLoginPage(String expectedFractionalURL) {
        String url = loginPage.getLoginPageURL();

        Assert.assertTrue(url.contains(expectedFractionalURL));

    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {

        accountpage = loginPage.doLogin(username, password);
        acctpage_tittle = accountpage.getaccountpageTittle();
    }

    @Then("the user should be redirected to the accounts page with title {string}")
    public void theUserShouldBeRedirectedToTheAccountsPageWithTitle(String expectedTittle) {
        Assert.assertEquals(acctpage_tittle, expectedTittle);

    }

    @When("the user checks the forgot password link")
    public void theUserChecksTheForgotPasswordLink() {
         buttonexist = loginPage.isForgottenPasswordLinkExist();
        Assert.assertTrue(buttonexist);

    }

    @Then("the forgot password link should be displayed")
    public void theForgotPasswordLinkShouldBeDisplayed() {
        Assert.assertTrue(buttonexist);
    }


}



