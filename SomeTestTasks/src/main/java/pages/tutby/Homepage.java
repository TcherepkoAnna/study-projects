package pages.tutby;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Homepage {

    WebDriver driver;

    @FindBy(how = How.ID, using = "search_from_str")
    WebElement searchInput;

    @FindBy(how = How.XPATH, using = "//input[@name='search'][@type='submit']")
    WebElement searchButton;

    public Homepage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchSite(String input){
        searchButton.sendKeys(input);
        searchButton.click();
    }
}
