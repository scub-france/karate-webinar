package glue;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepDefinitions {
    WebDriver driver = new ChromeDriver();

    @Given("I am on the base URL")
    public void i_am_on_the_base_URL() {
        driver.get("https://martinfowler.com");
    }

    @When("I click on the link with href {string}")
    public void i_click_on_the_link_with_href(String navPath) {
        WebElement link = driver.findElement(By.xpath("//a[@href='" + navPath + "']"));
        link.click();
    }
    @And("I wait for an element containing text {string}")
    public void i_wait_for_an_element_containing_text(String textContent) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // or any other desired timeout duration
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + textContent + "')]")));
    }
    @And("I verify an element with href {string} exists")
    public void i_verify_an_element_with_href_exists(String existingPath) {
        WebElement element = driver.findElement(By.xpath("//a[@href='" + existingPath + "']"));
        Assert.assertNotNull(element);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
