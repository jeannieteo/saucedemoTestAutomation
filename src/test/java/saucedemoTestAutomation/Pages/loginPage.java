package saucedemoTestAutomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage extends basePage {
    public loginPage(WebDriver driver) {
        super(driver); //The 'super' keyword allows referencing the parent class or superclass of a subclass in Java.
        // It is often employed to access members (fields or methods) of the superclass that have been overridden in the
        // subclass. You can call the superclass's method from within the subclass using super.
        driverL = driver;

    }

    WebDriver driverL;
    private String userNameInput = "user-name";
    private String passwordInput = "password";
    private String loginButton = "login-button";
    //private String shoppingCart = "shopping_cart_container";
    private String burgerMenu = "react-burger-menu-btn";
    private String logoutLink = "logout_sidebar_link";
    private String errorMessage = "//*[@id='login_button_container']/div/form/div[3]/h3";


    //methods
    public productPage login(String username, String password) {
        driverL.findElement(By.id(userNameInput)).clear();
        if (username.equalsIgnoreCase("null") )  {
            driverL.findElement(By.id(userNameInput)).sendKeys("");
        }else{
            driverL.findElement(By.id(userNameInput)).sendKeys(username);
        }
        driverL.findElement(By.id(passwordInput)).clear();
        if (password.equalsIgnoreCase("null") )  {
            driverL.findElement(By.id(passwordInput)).sendKeys("");
        }else{
            driverL.findElement(By.id(passwordInput)).sendKeys(password);
        }
        driverL.findElement(By.id(loginButton)).click();
        return new productPage(driverL);
    }

    public void logout() {

        //explicit wait
        WebElement burger = driverL.findElement(By.id(burgerMenu));
        WebDriverWait wait = new WebDriverWait(driverL, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(burger));
        burger.click();

        WebElement logoutMenu = driverL.findElement(By.id(logoutLink));
        wait.until(ExpectedConditions.elementToBeClickable(logoutMenu));
        logoutMenu.click();

    }

    public String getErrorMessageText()  {
        WebDriverWait wait = new WebDriverWait(driverL, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(errorMessage)));

        return driverL.findElement(By.xpath(errorMessage)).getText();
    }
}