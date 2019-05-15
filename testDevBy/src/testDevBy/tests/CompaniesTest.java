package testDevBy.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testDevBy.config.Config;
import testDevBy.drivers.ChromeCreator;
import testDevBy.drivers.FirefoxCreator;
import testDevBy.pages.CompaniesPage;
import testDevBy.pages.CompanyPage;
import testDevBy.pages.HomePage;

public class CompaniesTest {
    private final static Logger LOG = Logger.getLogger(CompaniesTest.class);

    private WebDriver driver;

    private Config config = new Config();

    @Parameters({"Browser"})
    @BeforeTest
    public void setup(String browserName) {
        try {
            if (browserName.equals("Firefox")) {
                driver = new FirefoxCreator().createDriver();
            } else if (browserName.equals("Chrome")) {
                driver = new ChromeCreator().createDriver();
            }
            driver.get(config.getBaseUrl());
        } catch (WebDriverException exception) {
            LOG.error("Exception occurred: " + exception.getMessage());
        }
    }

    @Test
    public void testCompanies() {
        try {
            HomePage homePage = new HomePage(driver);
            CompaniesPage companiesPage = homePage.gotoCompaniesPage();
            for (int i = 1; i <= config.getCompaniesNumber(); i++) {
                CompanyPage companyPage = companiesPage.gotoCompanyPage(i);
                verifyContacts(companyPage);
                driver.navigate().back();
            }
        } catch (WebDriverException exception) {
            LOG.error("Exception occurred: " + exception.getMessage());
            assert (false);
        }
    }

    private void verifyContacts(CompanyPage companyPage) {
        String email = companyPage.getEmail();
        Assert.assertTrue(email != null && email != "", "Company [" + companyPage.getName() + "] email empty");
        LOG.info("Company [" + companyPage.getName() + "] email not empty");

        String phone = companyPage.getPhone();
        Assert.assertTrue(phone != null && phone != "", "Company [" + companyPage.getName() + "] phone empty");
        LOG.info("Company [" + companyPage.getName() + "] phone not empty");

        String site = companyPage.getSite();
        Assert.assertTrue(site != null && site != "", "Company [" + companyPage.getName() + "] site empty");
        LOG.info("Company [" + companyPage.getName() + "] site not empty");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
