package pages.tutby;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchResults {
    WebDriver driver;

    public SearchResults(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
