package Picsart;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssClass;

public class Utils {

    public static void checkForCssClassOfWebElement(SelenideElement element, boolean shouldHave, String cssClass) {
        if (shouldHave) {
            element.shouldHave(cssClass(cssClass));
        } else {
            element.shouldNotHave(cssClass(cssClass));
        }
    }
}
