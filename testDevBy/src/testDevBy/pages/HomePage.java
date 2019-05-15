package testDevBy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testDevBy.config.ObjectLocators;

public class HomePage {
    WebDriver driver;

    @FindBy(xpath = ObjectLocators.COMPANIES_PAGE)
    WebElement companiesLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CompaniesPage gotoCompaniesPage() {
        companiesLink.click();
        return new CompaniesPage(driver);
    }
}
