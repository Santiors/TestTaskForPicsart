package Picsart.Page;

import Picsart.User;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byTitle;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
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

    WebDriver driver = getWebDriver();


    public static HomePage getHomePage(User user) {
        return open(HomePage.BASE_URL, HomePage.class)
                .switchLanguage()
                .goToLogin()
                .acceptCookie()
                .loginWithEmail()
                .enterEmail(user.getUserName())
                .clickOnContinue()
                .enterPassword(user.getPassword())
                .logIn()
                .checkURL()
                .waitForPageLoading();
    }

    public HomePage waitForPageLoading() {
        logo.should(exist);
        newProjectButton.should(exist);
        return this;
    }

    protected HomePage goToLogin(){
        logInButtonOnTop.click();
        return this;
    }

    protected HomePage enterEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    protected HomePage enterPassword(String password) {
        passwordField.setValue(password);
        return this;
    }

    protected HomePage logIn() {
        logInButton.click();
        return this;
    }

    public HomePage clickOnNewProject(){
        newProjectButton.click();
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
        continueWithEmailButton.click();
        return this;
    }

    public HomePage clickOnContinue() {
        continueButton.click();
        return this;
    }

    public HomePage switchLanguage() {
        switchLanguageButton.click();
        englishLanguage.click();
        return this;
    }

    public HomePage acceptCookie() {
        acceptCookieButton.click();
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
        settingsButton.click();
        return this;
    }

}
