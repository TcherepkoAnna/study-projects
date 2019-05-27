package pages.onliner;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Seller {

    private WebDriver driver;
    private JavascriptExecutor jsExecutor;
    public static final Logger LOG = Logger.getLogger(Seller.class);
    public static final By SELLER_NAME_LOCATOR = By.xpath("//h1[@class='sells-title']");
    private String urlSeller;


    public Seller(WebDriver driver, String url) {
        this.driver = driver;
        this.urlSeller = url;
        PageFactory.initElements(driver, this);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public String getName() {
        LOG.debug("Getting name");
        String name = driver.findElement(SELLER_NAME_LOCATOR).getText();
        return name;
    }

    public boolean checkUrl() {
        return driver.getCurrentUrl().contains(urlSeller);
    }

}
