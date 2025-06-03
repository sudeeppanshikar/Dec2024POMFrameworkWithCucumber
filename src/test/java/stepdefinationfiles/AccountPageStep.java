package stepdefinationfiles;

import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.sun.source.tree.AssertTree;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


import java.util.List;
import java.util.Properties;

public class AccountPageStep {

    LoginPage loginPage;
    AccountPage accountpage;
    Properties prop;
    SearchResultsPage searchrespage;
    List<String> actualheaderList;
    String acctPageURL;

    public AccountPageStep(Hook hook) {
        this.loginPage = hook.getloginpage();
        this.prop = hook.getProp();
    }

    @Given("the user is in account page with the page title {string}")
    public void theUserIsInAccountPage(String expectedTittle) {
        accountpage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        String actualTittle = accountpage.getaccountpageTittle();
        Assert.assertEquals(expectedTittle, actualTittle);


    }

    @When("the user search for the product {string}")
    public void theUserSearchForTheProduct(String product_search) {
        searchrespage=accountpage.doSearch(product_search);
        
    }

    @Then("the product and no of image should match {int}")
    public void theProductAndNoOfImageShouldMatch(int resultsCount) {
       int actualcount= searchrespage.getResultsProductCount();
       Assert.assertEquals(resultsCount,actualcount);
    }

    @When("check for the account page header")
    public void checkForTheAccountPageHeader() {
        actualheaderList = accountpage.getAccountPageHeader();
        
    }

    @Then("the list of header should match")
    public void theListOfHeaderShouldMatch(DataTable expectedheaderData) {
        List<String> expectedHeaderList = expectedheaderData.asList();
        Assert.assertEquals(expectedHeaderList,actualheaderList);

    }

    @When("check for the account page URL")
    public void checkForTheAccountPageURL() {
         acctPageURL = accountpage.getaccountpageURL();

    }

    @Then("the URL should contain {string}")
    public void theURLShouldContain(String expectedFractionalURL) {
        Assert.assertTrue(acctPageURL.contains(expectedFractionalURL));
    }
}
