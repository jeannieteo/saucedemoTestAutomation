package com.saucedemoTest.testClasses;

import Utils.excelReaders;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class productTests extends baseTests{
    List<WebElement> productNames;
    List<WebElement> productPrices;
    @BeforeClass
    public void login_success()    {

        productPageP = loginPageB.login("standard_user", "secret_sauce");
        productNames = productPageP.get_product_names();
        productPrices = productPageP.get_product_prices();

    }

    @Test(dataProviderClass = excelReaders.class, dataProvider = "getProductData")
    public void check_the_product(String productName, String price)    {
        SoftAssert softAssert = new SoftAssert();
        boolean productFound = false;
        System.out.println("check_the_product: " + productName );
        for(int i = 0; i <productNames.size(); i++)  {
            System.out.println("=====" + productNames.get(i).getText());
            if (productName.equalsIgnoreCase( productNames.get(i).getText()) )    {//do the compare for name
                productFound = !productFound;
                //do the compare for price
                softAssert.assertTrue(price.equals(productPrices.get(i).getText()), productName +"-> Expected: " + price + " but got: " + productPrices.get(i).getText());
                System.out.println( "name and price: " + productName + " -> " + price);}
            }
        softAssert.assertTrue(productFound, productName + " not found.");
        softAssert.assertAll();
    }

}//end class


