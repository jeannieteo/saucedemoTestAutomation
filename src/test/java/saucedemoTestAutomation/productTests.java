package saucedemoTestAutomation;

import Utils.excelReaders;
import org.openqa.selenium.WebElement;
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

    @Test(dataProviderClass = excelReaders.class, dataProvider = "getProductData", enabled = false)
    public void check_the_products_names_prices(String productName, String price)    {
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

    @Test
    public void Remove_2_from_cart() {
        productPageP.remove_from_cart("remove-sauce-labs-fleece-jacket");
        productPageP.remove_from_cart("remove-sauce-labs-onesie");
        productPageP.verify_badge_count(2);
    }



}//end class


