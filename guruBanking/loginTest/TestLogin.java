package guruBanking.loginTest;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

public class TestLogin {

    private static WebDriver driver;
    private static String baseUrl; // BASE_URL of Website Guru99

    @Test(dataProvider = "loginCredentials")
    public void testLogin(String username, String password) throws Exception {

        String actualBoxtitle;

        driver.findElement(By.name("uid")).clear();
        driver.findElement(By.name("uid")).sendKeys(username);

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.name("btnLogin")).click();

        /* Determine "Pass - Fail" Status of the Script
         * If login credentials are correct,  Alert(Pop up) is NOT present. An Exception is thrown and code in catch block is executed
         * If login credentials are invalid, Alert is present. Code in try block is executed
         */
        try {
            Alert alt = driver.switchTo().alert();
            actualBoxtitle = alt.getText(); // get content of the Alter Message
            alt.accept();
            Assert.assertEquals(actualBoxtitle, Util.EXPECT_ERROR);
        } catch (NoAlertPresentException Ex) {

//            String pageText = driver.findElement(By.tagName("tbody")).getText();
            String pageText = driver.findElement(By.xpath("//tr[@class=\"heading3\"]/td")).getText();
            System.out.println(pageText);

            // Extract the dynamic text mngrXXXX on page
            String[] parts = pageText.split(Util.PATTERN);
            String dynamicText = parts[1];

            // Check that the dynamic text is of pattern mngrXXXXXX
            // First 4 characters must be "mngr"
            Assert.assertTrue(dynamicText.substring(1, 5).equals(Util.FIRST_PATTERN));
            String remain = dynamicText.substring(dynamicText.length() - 6);
            Assert.assertTrue(remain.matches(Util.SECOND_PATTERN));

            // take Screenshot
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("c:\\Users\\annat\\Downloads\\screenshot.png"));
        }
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @BeforeTest
    public void firefoxSetup() {

        System.setProperty("webdriver.gecko.driver", Util.DRIVER_PATH);
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(Util.FIREFOX_BINARY_PATH);
        options.setProfile(new FirefoxProfile());
        options.setCapability("marionette", true);
        driver = new FirefoxDriver(options);

        baseUrl = Util.BASE_URL;
        // Specifies the amount of time the driver should wait when searching for an element if it is not immediately present.
        driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
        driver.get(baseUrl + "V4/");
    }

    @BeforeMethod
    public void goToURL() {
        driver.navigate().to(baseUrl + "V4/");
    }


//    @DataProvider(name = "loginCredentials")
//    public Object[][] getTestData() throws Exception {
//        return Util.getDataFromExcel(Util.FILE_PATH,
//                Util.SHEET_NAME, Util.TABLE_NAME);
//    }

    @DataProvider(name = "loginCredentials")
    public Object[][] testData() {

        Object[][] data = new Object[4][2];

        // 1st row
        data[0][0] = Util.USER_ID;
        data[0][1] = Util.PSSWRD;
        //2nd row
        data[1][0] = "invalid";
        data[1][1] = "valid";
        //3rd row
        data[2][0] = "valid";
        data[2][1] = "invalid";
        //4th row
        data[3][0] = "invalid";
        data[3][1] = "invalid";
        return data;
    }
}
