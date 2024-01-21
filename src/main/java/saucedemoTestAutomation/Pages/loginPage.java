package saucedemoTestAutomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage extends basePage {
    public loginPage(WebDriver driver) {
        super(driver);
        driverL = driver;

    }

    WebDriver driverL;
    private String userNameInput = "user-name";
    private String passwordInput = "password";
    private String loginButton = "login-button";
    //private String shoppingCart = "shopping_cart_container";
    private String burgerMenu = "react-burger-menu-btn";
    private String logoutLink = "Logout";
    private String errorMessage = "//*[@id='login_button_container']/div/form/div[3]/h3";


    //methods
    public productPage login(String username, String password) {
        driverL.findElement(By.id(userNameInput)).clear();
        driverL.findElement(By.id(userNameInput)).sendKeys(username);
        driverL.findElement(By.id(passwordInput)).clear();
        driverL.findElement(By.id(passwordInput)).sendKeys(password);

        driverL.findElement(By.id(loginButton)).click();
        return new productPage(driverL);
    }

    public void logout() {

        //explicit wait
        WebElement burger = driverL.findElement(By.id(burgerMenu));
        WebDriverWait wait = new WebDriverWait(driverL, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(burger));
        burger.click();

        WebElement logoutMenu = driverL.findElement(By.linkText(logoutLink));
        wait.until(ExpectedConditions.elementToBeClickable(logoutMenu));
        logoutMenu.click();

    }

    public String getErrorMessageText()  {
        WebDriverWait wait = new WebDriverWait(driverL, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(errorMessage)));

        return driverL.findElement(By.xpath(errorMessage)).getText();
    }
}