package Picsart;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.BeforeClass;

import static Picsart.TestUser.initializeUsers;
import static java.util.Objects.nonNull;

public abstract class FunctionalTest {

    private static boolean isConfigured;

    public static Logger log = LogManager.getLogger();

    @BeforeClass
    public static synchronized void configure() {
        log.info("===================TEST STARTED===================");

        if (isConfigured) {
            return;
        }

        initializeUsers();

        if (nonNull(System.getProperty("chromeoptions.mobileEmulation"))) {
            Configuration.browser = "chrome";
        }

        Configuration.timeout = 8000;
        Configuration.browserCapabilities.setCapability("enableVNC", true);

        log.info("browserName=" + Configuration.browser + ", " + "browserVersion=" + Configuration.browserVersion);

        isConfigured = true;
    }

    @After
    public void quite() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.getWebDriver().quit();
        }
        log.info("===================TEST FINISHED===================");
    }
}
