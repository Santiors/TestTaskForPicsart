package Picsart.Page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HomePage {

    public static final String BASE_URL = "https://picsart.com/";

    private SelenideElement emailField = $(byAttribute("data-testid", "email-field-input"));
    private SelenideElement passwordField = $(byAttribute("data-testid", "insertPassword-input"));
    private SelenideElement logInButton = $(byText("Log in"));
    private SelenideElement logInButtonOnTop = $(byAttribute("data-test", "login-signup-button"));
    private SelenideElement continueButton = $(byText("Continue"));
    private SelenideElement continueWithEmailButton = $(byText("Continue with Email"));
    private SelenideElement settingsButton = $(byAttribute("data-test","settings-button"));
    public  SelenideElement logo = $(byAttribute("data-test", "picsart-logo"));
    private SelenideElement newProjectButton = $(byAttribute("data-testid", "create-search-button"));
    public  SelenideElement profileButton = $(byTitle("User avatar"));
    public SelenideElement logoutButton = $(byAttribute("data-test","logout-button"));
    public SelenideElement switchLanguageButton = $(byAttribute("data-testid","language-switcher"));
    public SelenideElement englishLanguage = $(byText("English"));
    public SelenideElement acceptCookieButton = $(byId("onetrust-accept-btn-handler"));

    public static Logger log = LogManager.getLogger();

    WebDriver driver = getWebDriver();

    public HomePage waitForPageLoading() {
        logo.should(exist);
        newProjectButton.should(exist);
        return this;
    }

    protected HomePage goToLogin(){
        try {
            logInButtonOnTop.click();
        } catch (Exception e) {
            log.error("Can't Login into account");
        }
        return this;
    }

    protected HomePage enterEmail(String email) {
        try {
            emailField.setValue(email);

        } catch (Exception e){
            log.error("Can't enter e-mail");
        }
        return this;
    }

    protected HomePage enterPassword(String password) {
        try {
            passwordField.setValue(password);
        } catch (Exception e){
        log.error("Can't enter password");
    }
        return this;
    }

    protected HomePage logIn() {
        try {
            logInButton.click();
        } catch (Exception e){
            log.error("Couldn't login into account");
        }
        return this;
    }

    public HomePage clickOnNewProject(){
        try {
            newProjectButton.click();
        } catch (Exception e){
            log.error("Can't create a new project");
        }
        return this;
    }

    public HomePage checkURL() {
        assertThat(isThisPage(), is(true));
        return this;
    }

    private boolean isThisPage() {
        return WebDriverRunner.url().contains(BASE_URL);
    }

    public HomePage loginWithEmail() {
        try {
            continueWithEmailButton.click();
        } catch (Exception e) {
            log.error("Can't click on Continue With Email");
        }
        return this;
    }

    public HomePage clickOnContinue() {
        try {
            continueButton.click();
        } catch (Exception e) {
            log.error("Can't click on Continue");
        }
        return this;
    }

    public HomePage switchLanguage() {
        try {
            switchLanguageButton.click();
            englishLanguage.click();
        } catch (Exception e){
            log.error("Can't switch to English Language");
        }
        return this;
    }

    public HomePage acceptCookie() {
        try {
            acceptCookieButton.click();
        } catch (Exception e) {
            log.error("Can't accept cookie");
        }
        return this;
    }

    public HomePage moveToHover() {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(profileButton)
                .build()
                .perform();
        return this;
    }

    public HomePage goToSettings() {
        try {
            settingsButton.click();
        } catch (Exception e) {
            log.error("Can't go to Settings Page");
        }
        return this;
    }

}
