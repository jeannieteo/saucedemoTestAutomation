package saucedemoTestAutomation;
import Utils.excelReaders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;


public class loginTests extends baseTests {
    //logging
    //Logger logger = LogManager.getLogger(loginTests.class);
    @Test(dataProviderClass = excelReaders.class, dataProvider = "getLoginData")
    public void login_UnSuccessful(String username, String password, String expectedError) {
        loginPageB.login(username, password);
        logger.debug("Test: login_UnSuccessful username : " + username + " pw: " + password + "Expected: " + expectedError + "\n");
        String actualMessage = loginPageB.getErrorMessageText();
        Assert.assertEquals(actualMessage, expectedError, "Actual error message and expected error not same.");
    }

    //@Test
    //public void login_successful()    {
    //    loginPageB.login("standard_user", "secret_sauce");
    //
    //}
    //@AfterMethod
   // public void logOut() {
   //     if (loggedIn)   {
   //         loginPageB.logout();
   //     }
   // }


}
