package saucedemoTestAutomation;
import Utils.excelReaders;
import org.testng.Assert;
import org.testng.annotations.*;


public class loginTests extends baseTests {
    boolean loggedIn = false;
    @Test(dataProviderClass = excelReaders.class, dataProvider = "getLoginData")
    public void login_UnSuccessful(String username, String password, String expectedError)    {
        loginPageB.login(username, password);

        System.out.println("log in: " + username + " pw: " + password + "Expected: " + expectedError + "\n");
        String actualMessage = loginPageB.getErrorMessageText();
        Assert.assertEquals(actualMessage, expectedError, "Actual error message and expected error not same.");
    }

    //@Test
    //public void login_successful()    {
    //    loginPageB.login("standard_user", "secret_sauce");
    //
    //}
    @AfterMethod
    public void logOut() {
        if (loggedIn)   {
            loginPageB.logout();
            }
    }


}
