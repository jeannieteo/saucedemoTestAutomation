package saucedemoTestAutomation;
import org.apache.logging.log4j.LogManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.Logger;
import saucedemoTestAutomation.Pages.loginPage;

import saucedemoTestAutomation.Pages.productPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.*;

import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class baseTests {
    public WebDriver driver;
    //logging
    Logger logger = LogManager.getLogger(baseTests.class);
    //declare initialize and clone object from class
    protected loginPage loginPageB;
    protected productPage productPageP;
    public boolean loggedIn = false;
    //protected resultsPage resultsPageT;

    @BeforeClass
    @Parameters(value={"TestURL", "Browser"})
    public void commonSetUp(String testURL, String browser) {
        logger.info("-----SETUP ---");
        if (browser.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            driver = new ChromeDriver(options);

        }else if (browser.equalsIgnoreCase("Firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new FirefoxDriver(options);

        }else if (browser.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();

        }else if (browser.equalsIgnoreCase("IE")) {
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
            options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
            options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
            options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            driver = new InternetExplorerDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //driver.manage().window().maximize();
        driver.get(testURL);
        logger.info("Driver get url: " + testURL + "on browser: " + browser + ". ");
        loginPageB = new loginPage(driver);
        productPageP = new productPage(driver);

    }

    @AfterClass
    public void commonTearDown()  {
        logger.info("-----TearDOWN ---");
        if (loggedIn)   {
            loginPageB.logout();
        }

        //driver.quit();
    }


    @AfterMethod
    public void takeScreenshotForFailures(ITestResult testResult)	{

        if(ITestResult.FAILURE == testResult.getStatus())	{
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") + "/target/screenshots/" + testResult.getName() + ".png");
            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

