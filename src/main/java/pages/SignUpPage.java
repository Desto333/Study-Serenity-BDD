package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

import java.util.List;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

@DefaultUrl("https://www.spotify.com/us/signup/")
public class SignUpPage extends PageObject {

    private final By emailField = By.cssSelector("input#email");
    private final By confirmEmailField = By.cssSelector("input#confirm");
    private final By passwordField = By.cssSelector("input#password");
    private final By nameField = By.cssSelector("input#displayname");
    private final By monthDropDown = By.cssSelector("select#month");
    private final String monthDropDownOption = "//select[@id='month']/option[contains(text(),'%s')]";
    private final By dayField = By.cssSelector("input#day");
    private final By yearField = By.cssSelector("input#year");
    private final String genderOption = "//label/span[text()='%s']";
    private final String shareCheckbox = "//span[contains(text(),'Share my registration')]";
    private final By signUpButton = By.xpath("//div[starts-with(@class,'SignupButton')]/button");
    private final String errorLabel = "//div[contains(@class,'InputErrorMessage') and string-length(text()>0)]";
    private final String errorLabelByText = "//div[starts-with(@class,'FormHelpText') and contains(text(),\"%s\")]";
    private final By cookiesWarningCloseButtonXpath = By.xpath("//div[@id='onetrust-close-btn-container']/button");

    public SignUpPage closeCookiesWarning() {
        if (find(cookiesWarningCloseButtonXpath).isDisplayed()) {
            find(cookiesWarningCloseButtonXpath).click();
        }
        return this;
    }

    public SignUpPage typeEmail(String email) {
        find(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        find(confirmEmailField).sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        find(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeName(String name) {
        find(nameField).sendKeys(name);
        return this;
    }

    public SignUpPage setMonth(String month) {
        find(monthDropDown).click();
        find(xpath(format(monthDropDownOption, month))).waitUntilVisible().click();
        return this;
    }

    public SignUpPage typeDay(String day) {
        find(dayField).sendKeys(day);
        return this;
    }

    public SignUpPage typeYear(String year) {
        find(yearField).sendKeys(year);
        return this;
    }

    public SignUpPage setGender(String gender) {
        find(new By.ByXPath(String.format(genderOption, gender))).click();
        return this;
    }

    public SignUpPage setShare(boolean value) {
        find(new By.ByXPath(shareCheckbox)).waitUntilClickable().click();
        return this;
    }

    public void pressSignUpButton() {
        find(signUpButton).click();
    }

    public List<WebElementFacade> getErrors() {
        return findAll(new By.ByXPath(errorLabel));
    }

    public WebElementFacade getErrorByText(String errorText) {
        return find(By.xpath(String.format(errorLabelByText, errorText)));
    }

    public WebElementFacade getErrorByNumber(int number) {
        return getErrors().get(number - 1);
    }

    public boolean isErrorVisible(String errorMessage) {
        return findAll(xpath(format(errorLabelByText, errorMessage))).size() > 0
                && findAll(xpath(format(errorLabelByText, errorMessage))).get(0).isDisplayed();
    }
}
