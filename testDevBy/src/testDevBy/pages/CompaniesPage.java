package testDevBy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testDevBy.config.ObjectLocators;

public class CompaniesPage {
    WebDriver driver;

    @FindBy(xpath = ObjectLocators.COMPANIES_TABLE)
    WebElement companiesTable;

    public CompaniesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CompanyPage gotoCompanyPage(int num) {
        getCompanyLink(num).click();
        return new CompanyPage(driver);
    }

    WebElement getCompanyLink(int num) {
        return companiesTable.findElement(By.xpath("//tr[" + num + "]/td[1]/a"));
    }
}
