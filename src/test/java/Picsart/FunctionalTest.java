package Picsart;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;

import static Picsart.TestUser.initializeUsers;
import static java.util.Objects.nonNull;

@Slf4j
public abstract class FunctionalTest {

    private static boolean isConfigured;

    @Rule
    public TestRule watcher = new TestWatcherRule();


    @BeforeClass
    public static synchronized void configure() {
        if (isConfigured) {
            return;
        }

        initializeUsers();

        if (nonNull(System.getProperty("chromeoptions.mobileEmulation"))) {
            Configuration.browser = "chrome";
        }

        Configuration.timeout = 8000;
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities.setCapability("enableVideo", true);

        log.info("browserName=" + Configuration.browser + ", " + "browserVersion=" + Configuration.browserVersion);

        isConfigured = true;
    }

    @After
    public void quite() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.getWebDriver().quit();
        }
    }
}
