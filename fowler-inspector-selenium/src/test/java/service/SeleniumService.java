package service;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

    /**
     * Initialize the WebDriver based on the "env" environment variable.
     */
    public void initializeDriver() {
        String browserType = System.getProperty("env");
        driver = "hub".equals(browserType) ? getHubDriver() : getLocalWebDriver();
    }

    /**
     * Navigate to the given URL.
     * @param url The URL to navigate to.
     */
    public void navigateToUrl(final String url) {
        driver.get(url);
    }

    /**
     * Wait for the element to be present on the page.
     * @param locator The locator for the element.
     * @param timeoutInSeconds The timeout in seconds.
     * @return The WebElement.
     */
    public WebElement waitForElement(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Verify that the element exists on the page.
     * @param elementXpath The XPath for the element.
     */
    public void verifyElementExistByXpath(final String elementXpath) {
        List<WebElement> elements = driver.findElements(By.xpath(elementXpath));
        Assert.assertFalse("The element with XPath [" + elementXpath + "] does not exist.", elements.isEmpty());
    }

    /**
     * Click on the element with the given XPath.
     * @param elementXpath The XPath for the element.
     */
    public void clickOnElementByXpath(final String elementXpath) {
        WebElement element = waitForElement(By.xpath(elementXpath), 3);
        element.click();
    }

    /**
     * Get the local WebDriver.
     * @return The WebDriver.
     */
    public WebDriver getLocalWebDriver() {
        return new ChromeDriver();
    }


    /**
     * Get the hub WebDriver.
     * @return The WebDriver.
     */
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

    /**
     * Close the browser.
     */
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
