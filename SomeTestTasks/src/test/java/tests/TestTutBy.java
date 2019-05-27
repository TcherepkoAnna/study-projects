package tests;

import config.Config;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import pages.tutby.Homepage;

public class TestTutBy extends TestBase {

    private final static Logger LOG = Logger.getLogger(TestTutBy.class);
    private Config config = new Config();


    @Test
    public void testSearch(){
        driver.get(config.getTutByUrl());
        Homepage homePage = new Homepage(driver);
        homePage.searchSite(config.getSearchInput());




    }
}
