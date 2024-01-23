package saucedemoTestAutomation.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class productPage extends basePage{
    public productPage(WebDriver driver) {
        super(driver);
        driverP =driver;
    }
    WebDriver driverP;

    private String productName = "inventory_item_name";
    private String productPrice = "inventory_item_price";
    private String cartBadge = "//span[@class='shopping_cart_badge']";
    private String cartLink = "//a[@class='shopping_cart_link']";

    public List<WebElement> get_product_names()   {
        List<WebElement> listProductName = driverP.findElements(By.className(productName));
        return listProductName;
    }

    public List<WebElement> get_product_prices()   {
        List<WebElement> listProductPrice = driverP.findElements(By.className(productPrice));
        return listProductPrice;
    }

    public void add_to_cart(String id)   {
        driverP.findElement(By.id(id)).click();
    }

    public void remove_from_cart(String id)   {
        driverP.findElement(By.id(id)).click();
    }
    public void verify_badge_count(int expected_count)   {
        String str_count = driverP.findElement(By.xpath(cartBadge)).getText();
        Assert.assertEquals(Integer.valueOf(str_count), expected_count);
    }
}
