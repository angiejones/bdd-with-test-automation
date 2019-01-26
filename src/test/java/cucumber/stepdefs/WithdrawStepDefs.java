package cucumber.stepdefs;

import base.BaseTests;
import cucumber.api.java.en.*;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.AccountActivityPage;
import services.Endpoints;
import utils.NavigationUtils;
import utils.ServicesUtils;

import static java.lang.String.*;
import static utils.ServicesUtils.HttpMethod.*;
import static io.restassured.path.json.JsonPath.from;

public class WithdrawStepDefs extends BaseTests {

    private Response response;
    private int accountId;

    @Given("a customer has an account")
    public void createNewAccount(){
        String customerId = System.getProperty("customer.id");
        String endpoint = format(Endpoints.CREATE_ACCOUNT, customerId);
        response = ServicesUtils.execute(endpoint, POST);
        accountId = from(response.asString()).get("id");
    }

    @And("^the account balance is (.*) dollars$")
    public void setAccountBalance(float desiredBalance) {
        float currentBalance = getCurrentBalance();
        if(desiredBalance != currentBalance){
            deposit(desiredBalance - currentBalance);
        }
    }

    @When("the customer withdraws (.*) dollars")
    public void withdraw(double withdrawAmount){
        String endpoint = format(Endpoints.WITHDRAW, accountId, withdrawAmount);
        response = ServicesUtils.execute(endpoint, POST);
    }

    @Then("the account balance should be (.*) dollars")
    public void verifyBalance(float balance){
        Assert.assertEquals(balance, getCurrentBalance(), 0.0f);
    }

    @Then("a new transaction should be recorded")
    public void verifyTransactionRecord(){
        AccountActivityPage activityPage =
                NavigationUtils.goToAccountActivity(driver, accountId);
        Assert.assertEquals(valueOf(accountId), activityPage.getAccountId());

        validateWindow();
    }

    private float getCurrentBalance(){
        String endpoint = format(Endpoints.GET_ACCOUNT, accountId);
        response = ServicesUtils.execute(endpoint, GET);
        return from(response.asString()).get("balance");
    }

    private void deposit(float amount){
        ServicesUtils.execute(format(Endpoints.DEPOSIT, accountId, amount), POST);
    }
}
