package tests;

import config.Config;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import util.FtpConnection;

import java.io.IOException;

public class TestFtp {

    private final static Logger LOG = Logger.getLogger(TestFtp.class);
    private Config config = new Config();
    private FtpConnection conn;

    @Test
    public void testFtp() {

        try {
            conn = new FtpConnection(config.getFtpHostname());
            conn.connect();
            conn.login(config.getFtpUsername(), config.getFtpPassword());

            FTPFile[] files = conn.getDirectoriesList();
            if (files != null && files.length > 0) {
                for (FTPFile f : files) {
                    String name = f.getName();
                    LOG.info("directory: " + name);
                    conn.changeDirectory(name);
                    conn.createDirectory(config.getFtpDirToCreateName());
                    conn.deleteDirectory(config.getFtpDirToCreateName());
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                conn.disconnectFromServer();
            } catch (IOException ex) {
                LOG.error(ex.getMessage());
            }
        }
    }


}
