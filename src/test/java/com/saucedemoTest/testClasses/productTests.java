package com.saucedemoTest.testClasses;

import Utils.excelReaders;
import org.testng.annotations.Test;

public class productTests {

    @Test(dataProviderClass = excelReaders.class, dataProvider = "getLoginData")
    public void printFirst(String username, String password)    {
        //getLoginData
        System.out.println("user: " + username + "pw: "+ password);

    }
}
