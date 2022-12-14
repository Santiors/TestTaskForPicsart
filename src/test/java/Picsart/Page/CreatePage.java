package Picsart.Page;

import Picsart.User;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.java.Log;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CreatePage extends HomePage{

    public static final String RELATIVE_URL = BASE_URL + "create";

    private SelenideElement hintOverlay = $(byAttribute("data-testid","hint-main"));
    private SelenideElement closeHintsPopUpButton = $(byAttribute("data-testid","hints-close"));
    private SelenideElement layoutCategory = $(byAttribute("data-testid","layout-category"));
    private SelenideElement homePageButton = $(byAttribute("data-testid","ThemedBrandImg"));



    public static CreatePage getCreatePage(User user) {
        return (CreatePage) open(HomePage.BASE_URL, CreatePage.class)
                .switchLanguage()
                .goToLogin()
                .acceptCookie()
                .loginWithEmail()
                .enterEmail(user.getUserName())
                .clickOnContinue()
                .enterPassword(user.getPassword())
                .logIn()
                .clickOnNewProject();
    }

    //need to switch to another tab, because editor page opens in new tab
    public CreatePage switchToEditorTab(){
        try {
            Set<String> handles = getWebDriver().getWindowHandles();
            List<String> tabs = new ArrayList<String>(handles);
            driver.switchTo().window(tabs.get(1));
        } catch (Exception e) {
            log.error("Can't switch to editor tab");
        }
        return this;
    }

    public CreatePage checkDefaultPageState(){
        waitForPageLoading();
        checkURL();
        isThisPage();
        return this;
    }

    @Override
    public CreatePage waitForPageLoading() {
        hintOverlay.should(exist);
        return this;
    }

    public CreatePage checkURL() {
        assertThat(isThisPage(), is(true));
        return this;
    }

    private boolean isThisPage() {
        return WebDriverRunner.url().contains(RELATIVE_URL);
    }

    public CreatePage closeHints() {
        try {
            closeHintsPopUpButton.click();
        } catch (Exception e){
            log.error("Can't close hints popup");
        }

        return this;
    }

    public CreatePage checkDefaultCategory() {
        try {
            layoutCategory.is(enabled);
        } catch (Exception e) {
            log.error("Layout is not enabled");
        }
        return this;
    }

    public void logOut() {
        try {
            homePageButton.click();
            moveToHover();
            logoutButton.click();
        } catch (Exception e) {
            log.error("Can't logout from account");
        }
    }

}
