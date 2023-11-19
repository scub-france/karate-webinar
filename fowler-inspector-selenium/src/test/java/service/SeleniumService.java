package service;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumService {

    private WebDriver driver;

    public void initializeDriver() {
        if (this.driver == null) {
            this.driver = new ChromeDriver();
        }
    }

    public void navigateToUrl(final String url) {
        driver.get(url);
    }

    public WebElement waitForElement(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void verifyElementExistByXpath(final String elementXpath) {
        List<WebElement> elements = driver.findElements(By.xpath(elementXpath));
        Assert.assertFalse("The element with XPath [" + elementXpath + "] does not exist.", elements.isEmpty());
    }

    public void clickOnElementByXpath(final String elementXpath) {
        WebElement element = waitForElement(By.xpath(elementXpath), 1); // Adjust timeout as needed
        element.click();
    }

    public WebDriver getHubDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome"); // or "firefox"
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    public void generateReport() {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-json-report.json");

        String projectName = "Fowler Inspector";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
