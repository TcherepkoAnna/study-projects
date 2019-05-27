package drivers;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxCreator implements DriverCreator{
    private Config config = new Config();

    @Override
    public WebDriver createDriver() {
//        System.setProperty(config.DRIVER_NAME_FIREFOX, config.getDriverPathFirefox());
        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary(config.getFirefoxBinaryPath());
//        options.setProfile(new FirefoxProfile());
        WebDriver driver = new FirefoxDriver(options);
        return driver;
    }
}
