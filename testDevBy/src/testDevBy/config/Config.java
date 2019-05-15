package testDevBy.config;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final static Logger LOG = Logger.getLogger(Config.class);
    private Properties properties = new Properties();

    public static final String DRIVER_NAME_FIREFOX = "webdriver.gecko.driver";
    public static final String DRIVER_NAME_CHROME = "webdriver.chrome.driver";
    public static final String FIREFOX_BINARY_PATH = "firefox_binary_path";
    public static final String BASE_URL = "base_url";
    public static final String COMPANIES_NUMBER = "companies_number";

    public Config() {
        InputStream input = null;
        try {
            input = new FileInputStream("src\\config.properties");
            // load a properties file
            properties.load(input);
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                }
            }
        }
    }

    public String getDriverPathFirefox() {
        return properties.getProperty(DRIVER_NAME_FIREFOX);
    }

    public String getDriverPathChrome() {
        return properties.getProperty(DRIVER_NAME_CHROME);
    }

    public String getFirefoxBinaryPath() {
        return properties.getProperty(FIREFOX_BINARY_PATH);
    }

    public String getBaseUrl() {
        return properties.getProperty(BASE_URL);
    }

    public int getCompaniesNumber() {
        return Integer.valueOf(properties.getProperty(COMPANIES_NUMBER));
    }
}
