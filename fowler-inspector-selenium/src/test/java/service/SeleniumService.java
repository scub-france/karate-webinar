package service;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class SeleniumService {

    private WebDriver driver;

    public void initializeDriver() {
        if (this.driver == null) {
            this.driver = getHubDriver();
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

    public WebDriver getHubDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
