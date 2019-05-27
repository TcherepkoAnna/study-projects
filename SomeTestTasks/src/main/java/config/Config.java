package config;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public class Config {

    private final static Logger LOG = Logger.getLogger(Config.class);
    private Properties properties = new Properties();
    private static final String propertiePath = "src\\main\\resources\\config.properties";


    private static final String TUT_BY_URL = "tut_by_url";
    private static final String ONLINER_CATALOG_URL = "onliner_catalog_url";
    private static final String PREFERRED_BROWSER = "preferred_browser";
    private static final String WAIT_TIME = "wait";

    private static final String VIDEOCARDS_MAX_AGE = "videocards_no_older_than_years";
    private static final String VIDEOCARDS_MIN_SIZE = "videomemory_size_min";
    private static final String VIDEOCARDS_MAX_SIZE = "videomemory_size_max";
    private static final String VIDEOCARDS_MEMORY_TYPE = "videomemory_type";
    private static final String VIDEOCARDS_ORDER_BY = "order_videocards_by";
    private static final String VIDEOCARDS_MIN_RATING = "rating_min";

    private static final String DOWNLOAD_DIR = "download_directory";



    public static final String SEARCH_INPUT = "search_input";


    public Config() {

        try (FileInputStream input = new FileInputStream(new File(propertiePath));
             InputStreamReader reader = new InputStreamReader(input, Charset.forName("UTF-8"));) {
            // load a properties file
            properties.load(reader);

        } catch (IOException ex) {
            LOG.error(ex.getMessage());
        }

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


    public int getVideocardsMaxAge() {
        return Integer.valueOf(properties.getProperty(VIDEOCARDS_MAX_AGE));
    }

    public String getVideocardsMinSize() {
        return properties.getProperty(VIDEOCARDS_MIN_SIZE);
    }

    public String getVideocardsMaxSize() {
        return properties.getProperty(VIDEOCARDS_MAX_SIZE);
    }

    public String getVideocardsMemoryType() {
        return properties.getProperty(VIDEOCARDS_MEMORY_TYPE);
    }

    public String getVideocardsOrderBy() {
        return properties.getProperty(VIDEOCARDS_ORDER_BY);
    }

    public Double getVideocardsMinRating() {
        return Double.valueOf(properties.getProperty(VIDEOCARDS_MIN_RATING));
    }

    public String getDownloadDir() {
        return properties.getProperty(DOWNLOAD_DIR);
    }
}
