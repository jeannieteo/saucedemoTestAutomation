package saucedemoTestAutomation;

import Utils.excelReaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class productTests extends baseTests{
    //logging
    //Logger logger = LogManager.getLogger(productTests.class);
    List<WebElement> productNames;
    List<WebElement> productPrices;
    @BeforeClass
    public void login_success()    {

        productPageP = loginPageB.login("standard_user", "secret_sauce");
        logger.debug("ProductTests:login_success ");
        loggedIn = true;
    }

    @Test(dataProviderClass = excelReaders.class, dataProvider = "getProductData")
    public void check_the_products_names_prices(String productName, String price)    {
        SoftAssert softAssert = new SoftAssert();
        productNames = productPageP.get_product_names();
        productPrices = productPageP.get_product_prices();
        logger.debug("ProductTests: check_the_products_names_prices");
        boolean productFound = false;
        logger.debug("check_the_product: " + productName );
        for(int i = 0; i <productNames.size(); i++)  {
            logger.debug("=====" + productNames.get(i).getText());
            if (productName.equalsIgnoreCase( productNames.get(i).getText()) )    {//do the compare for name
                productFound = !productFound;
                //do the compare for price
                softAssert.assertTrue(price.equals(productPrices.get(i).getText()), productName +"-> Expected: " + price + " but got: " + productPrices.get(i).getText());
                logger.debug( "name and price: " + productName + " -> " + price);}
            }
        softAssert.assertTrue(productFound, productName + " not found.");
        softAssert.assertAll();
    }

    @Test
    public void Add_1_to_cart() {
        productPageP.add_to_cart("add-to-cart-sauce-labs-backpack");
        productPageP.verify_badge_count(1);
    }

    @Test
    public void Add_2_to_cart() {
        //add-to-cart-sauce-labs-fleece-jacket
        productPageP.add_to_cart("add-to-cart-sauce-labs-fleece-jacket");
        productPageP.add_to_cart("add-to-cart-sauce-labs-onesie");
        productPageP.verify_badge_count(3);
    }
    @Test
    public void Remove_1_from_cart() {
        productPageP.remove_from_cart("remove-sauce-labs-backpack");
        productPageP.verify_badge_count(2);
    }

    //@Test
    //public void Remove_2_from_cart() {
    //    productPageP.remove_from_cart("remove-sauce-labs-fleece-jacket");
    //    productPageP.remove_from_cart("remove-sauce-labs-onesie");
    //productPageP.verify_badge_count(2);
    //}



}//end class


