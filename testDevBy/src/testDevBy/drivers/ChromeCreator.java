package testDevBy.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testDevBy.config.Config;

public class ChromeCreator implements DriverCreator {

    private Config config = new Config();
    @Override
    public WebDriver createDriver() {

        System.setProperty(config.DRIVER_NAME_CHROME, config.getDriverPathChrome());
        return new ChromeDriver();
    }
}
