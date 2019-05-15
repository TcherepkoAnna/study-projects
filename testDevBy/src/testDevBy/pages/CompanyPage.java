package testDevBy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testDevBy.config.ObjectLocators;

public class CompanyPage {
    WebDriver driver;
    @FindBy(tagName =  ObjectLocators.COMPANY_NAME)
    WebElement name;

    @FindBy(xpath = ObjectLocators.COMPANY_EMAIL)
    WebElement email;

    @FindBy(xpath = ObjectLocators.COMPANY_PHONE)
    WebElement phone;

    @FindBy(xpath = ObjectLocators.COMPANY_SITE)
    WebElement site;

    public CompanyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getEmail() {
        return email.getText();
    }

    public String getPhone() {
        return phone.getText();
    }

    public String getSite() {
        return site.getText();
    }

    public String getName() {
        return name.getText();
    }
}
