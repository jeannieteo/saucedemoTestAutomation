JAVA TESTNG SELENIUM

To run:
mvn clean test


Folder structure<br />
**--src----**</span><br />
&emsp;|**--main----**<br />
    &emsp;&emsp;&emsp;|**--saucedemoTestAutomation----** <br />
&emsp;&emsp;&emsp;&emsp;|**--resources-----** <br />
    &emsp;&emsp;&emsp;&emsp;&emsp;--log4J2.properties-----<br />
    &emsp;&emsp;&emsp;&emsp;&emsp;--saucedemotestdata/xlsx-----excel file for testdata <br /> 
&emsp;&emsp;&emsp;&emsp;|**--java-----**<br />
&emsp;|**--test----**<br />
    &emsp;&emsp;|**--java-----** <br />
    &emsp;&emsp;&emsp;|**--saucedemoTestAutomation----** folder contains tests for respective pages of the website<br />
     &emsp;&emsp;&emsp;&emsp;baseTests.java<br />
     &emsp;&emsp;&emsp;&emsp;loginTests.java<br />
     &emsp;&emsp;&emsp;&emsp;productTests.java<br />
    &emsp;&emsp;&emsp;&emsp;|**--Pages----** folder contains locators and actions for respective pages of the website<br />
     &emsp;&emsp;&emsp;&emsp;&emsp;basePage.java<br />
     &emsp;&emsp;&emsp;&emsp;&emsp;loginPage.java<br />
     &emsp;&emsp;&emsp;&emsp;&emsp;productPage.java<br />
    &emsp;&emsp;&emsp;|**--Utils----** folder contains utilities<br />
     &emsp;&emsp;&emsp;&emsp;excelReaders.java<br />
pom.xml<br />
testng_login.xml<br />
testng_product.xml<br />
