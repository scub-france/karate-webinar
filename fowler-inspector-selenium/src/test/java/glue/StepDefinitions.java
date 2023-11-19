package glue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import service.SeleniumService;

public class StepDefinitions {

    private SeleniumService seleniumService;

    // PicoContainer injects SeleniumService
    public StepDefinitions(SeleniumService seleniumService) {
        this.seleniumService = seleniumService;
    }

    @Before
    public void setup() {
        seleniumService.initializeDriver();
    }


    @Given("I am on the base URL")
    public void i_am_on_the_base_URL() {
        seleniumService.navigateToUrl("https://martinfowler.com");
    }

    @When("I click on the link with href {string}")
    public void i_click_on_the_link_with_href(String navPath) {
        seleniumService.clickOnElementByXpath("//a[@href='" + navPath + "']");
    }

    @And("I wait for an element containing text {string}")
    public void i_wait_for_an_element_containing_text(String textContent) {
//        seleniumService.waitForElementByXpath("//*[contains(text(),'" + textContent + "')]");
        seleniumService.verifyElementExistByXpath("//*[contains(text(),'" + textContent + "')]");
    }

    @Then("I verify an element with href {string} exists")
    public void i_verify_an_element_with_href_exists(String existingPath) {
        seleniumService.verifyElementExistByXpath("//a[@href='" + existingPath + "']");
    }

    @After
    public void tearDown() {
        seleniumService.closeBrowser();
    }
}
