package testDevBy.config;


public class ObjectLocators {

    public static final String COMPANIES_PAGE = "//a[text()='Компании']";
    public static final String COMPANIES_TABLE = "//table[@class='companies style-table dataTable']/tbody";
    public static final String XPATH_CONTACTS = "//*[@class='sidebar-views-contacts h-card vcard']/ul/li";

    public static final String COMPANY_EMAIL = XPATH_CONTACTS + "[1]/span";
    public static final String COMPANY_PHONE = XPATH_CONTACTS + "[2]/span";
    public static final String COMPANY_SITE = XPATH_CONTACTS + "[3]/a";
    public static final String COMPANY_NAME = "h1";


}

