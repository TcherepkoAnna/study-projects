package config;

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
    public static final String TUT_BY_URL = "tut_by_url";
    public static final String ONLINER_CATALOG_URL = "onliner_catalog_url";
    public static final String PREFERRED_BROWSER = "preferred_browser";
    public static final String WAIT_TIME = "wait";
    public static final String SEARCH_INPUT = "search_input";


    public Config() {
        InputStream input = null;
        try {
            input = new FileInputStream("src\\main\\resources\\config.properties");
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

    public String getTutByUrl() {
        return properties.getProperty(TUT_BY_URL);
    }

    public String getPreferredBrowser() {
        return properties.getProperty(PREFERRED_BROWSER);
    }


    public long getWaitTime() {
        return Long.valueOf(properties.getProperty(WAIT_TIME));
    }

    public String getSearchInput() {
        return properties.getProperty(SEARCH_INPUT);
    }

    public String getOnlinerCatalogUrl() {
        return properties.getProperty(ONLINER_CATALOG_URL);
    }


}
