package tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import pages.ruFilesFm.DownloadPage;

import java.io.IOException;

public class TestDownloadWget extends TestBase {

    private final static Logger LOG = Logger.getLogger(TestDownloadWget.class);

//    @Test
//    public void downloadFileUsingWget() {
//        String url = getDownloadLink();
//        driver.get(url);
//        DownloadPage downloadPage = new DownloadPage(driver, url);
//        String sourceLocation = downloadPage.getDownloadLink();
//        LOG.info("download source: " + sourceLocation + " for file: " + downloadPage.getFileName());
//        String wget_command = "cmd /c C:\\Wget\\wget.exe -P D: --no-check-certificate " + sourceLocation;
//
//        try {
//            Process exec = Runtime.getRuntime().exec(wget_command);
//            int exitVal = exec.waitFor(); // will wait untill wget completes the download
//            System.out.println("Exit value: " + exitVal);
//        } catch (InterruptedException | IOException ex) {
//            System.out.println(ex.toString());
//        }
//    }

    private static String getDownloadLink() {
        return "https://ru.files.fm/u/qcjjsy8b";

    }


}
