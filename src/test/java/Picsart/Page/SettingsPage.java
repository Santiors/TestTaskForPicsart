package Picsart.Page;

import Picsart.User;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SettingsPage extends HomePage{

    public static final String RELATIVE_URL = BASE_URL + "settings";

    private SelenideElement uploadButton = $(byText("Upload"));
    private SelenideElement repeatableUploadButton = $(byText("Upload New Photo"));
    private SelenideElement saveChangesButton = $(byAttribute("data-test", "save-changes-button"));

    public static SettingsPage getSettingsPage(User user) {
        return (SettingsPage) open(HomePage.BASE_URL, SettingsPage.class)
                .switchLanguage()
                .goToLogin()
                .acceptCookie()
                .loginWithEmail()
                .enterEmail(user.getUserName())
                .clickOnContinue()
                .enterPassword(user.getPassword())
                .logIn()
                .moveToHover()
                .goToSettings();
    }

    public SettingsPage checkDefaultPageState() {
        waitForPageLoading();
        checkURL();
        checkForInfoMessage();
        return this;
    }

    public SettingsPage waitForPageLoading() {
        $(byText("Settings")).should(exist);
        $(byAttribute("data-testid","tabs")).should(exist);
        return this;
    }

    public SettingsPage checkURL() {
        assertThat(isThisPage(), is(true));
        return this;
    }

    private boolean isThisPage() {
        return WebDriverRunner.url().contains(RELATIVE_URL);
    }

    public SettingsPage clickOnUpload() {
        try {
            repeatableUploadButton.click();
        } catch (Exception e) {
            uploadButton.click();
        }
        return this;
    }

    public SettingsPage uploadImage() throws AWTException {
            $("#upload-avatar").uploadFile(new File("src/test/java/Picsart/data/testImg.png"));
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
        return this;
    }

    public SettingsPage checkForInfoMessage() {
        try {
            $(byText("You can upload jpg. or png image files. Max size 2mb.")).should(exist);
        } catch (Exception e) {
            log.error("No info text");
        }
        return this;
    }

    public SettingsPage clickOnSaveChanges() {
        try {
            saveChangesButton.click();
        } catch (Exception e) {
            log.error("Can't save changes");
        }
        return this;
    }

    public void logOut() {
        try {
            moveToHover();
            logoutButton.click();
        } catch (Exception e) {
            log.error("Can't logout from account");
        }
    }
}
