package liveGuru99;

import guruBanking.loginTest.Util;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class testMobileList {

    private static WebDriver driver;
    private static String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    public int scc = 0;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.gecko.driver", liveGuru99.Util.DRIVER_PATH);
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(liveGuru99.Util.FIREFOX_BINARY_PATH);
        options.setProfile(new FirefoxProfile());
        options.setCapability("marionette", true);
        driver = new FirefoxDriver(options);

        baseUrl = liveGuru99.Util.BASE_URL;
        // Specifies the amount of time the driver should wait when searching for an element if it is not immediately present.
        driver.manage().timeouts().implicitlyWait(liveGuru99.Util.WAIT_TIME, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void verifyMobiles() throws IOException {

        //Step 1. Verify Title
        String demoSite = driver.findElement(By.cssSelector("h2")).getText();
        System.out.println(demoSite);
        try {
            Assert.assertEquals("THIS IS DEMO SITE FOR   ", demoSite);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        // 2 verify title of mobile
//        driver.findElement(By.cssSelector("li.level0:nth-child(1) > a:nth-child(1)")).click();
        driver.findElement(By.xpath("//nav/ol/li[1]/a")).click();
        Assert.assertTrue(driver.getTitle().equals(liveGuru99.Util.TITLE_MOBILE));

        // 3  order by Name
        Select drpOrder = new Select(driver.findElement(By.xpath("//select[@title=\"Sort By\"]")));
//        driver.findElement(By.cssSelector("select[title=\"Sort By\"]"));
        drpOrder.selectByVisibleText("Name");

        // 4 verify order
        List<WebElement> mobiles = driver.findElements(By.className("product-name"));
        Assert.assertTrue(isSorted(mobiles));
//        // this will take a screen shot of the manager's page after a successful login
//        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String png = ("C:\\Guru99 eCommerce Live Project\\Day01_TestCase1\\Mobile Products are sorted" + scc + ".png");
//        FileUtils.copyFile(scrFile, new File(png));

        // 5 compare price of xperia
        String XPeriaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
        driver.findElement(By.xpath("//a[@title=\"Sony Xperia\"]")).click();
        // price from details page
        String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();
        Assert.assertEquals(XPeriaPrice, detailPrice);


    }

    private boolean isSorted(List<WebElement> mobiles) {
        for (int i = 1; i < mobiles.size(); i++) {
            if (mobiles.get(i).getText().compareTo(mobiles.get(1 - 1).getText()) < 0) {
                return false;
            }
        }
        return true;
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
