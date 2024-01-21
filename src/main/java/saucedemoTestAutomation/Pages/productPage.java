package saucedemoTestAutomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class productPage extends basePage{
    public productPage(WebDriver driver) {
        super(driver);
        driverP =driver;
    }
    WebDriver driverP;

    private String productName = "inventory_item_name";
    private String productPrice = "inventory_item_price";

    public List<WebElement> get_product_names()   {
        List<WebElement> listProductName = driverP.findElements(By.className(productName));
        return listProductName;
    }

    public List<WebElement> get_product_prices()   {
        List<WebElement> listProductPrice = driverP.findElements(By.className(productPrice));
        return listProductPrice;
    }
}
