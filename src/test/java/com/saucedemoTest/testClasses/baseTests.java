package com.saucedemoTest.testClasses;
import com.saucedemoTest.Pages.loginPage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
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
    //declare initialize and clone object from class
    protected loginPage loginPageB;
    //protected allCoursesPage allCoursesPage;
    //protected resultsPage resultsPageT;

    @BeforeClass
    @Parameters(value={"TestURL", "Browser"})
    public void commonSetUp(String testURL, String browser) {
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
        loginPageB = new loginPage(driver);

    }

    @AfterClass
    public void commonTearDown()  {

        //driver.quit();
    }


    @AfterMethod
    public void takeScreenshotForFailures(ITestResult testResult)	{
        if(ITestResult.FAILURE == testResult.getStatus())	{
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") + "/test-output/screenshots/" + testResult.getName() + ".png");
            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

