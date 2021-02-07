package steps;

import net.thucydides.core.annotations.Step;
import pages.SignUpPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpSteps {
    SignUpPage page;

    @Step
    public void open_signup_page() {
        page.open();
    }

    @Step
    public void close_cookies_warning() {
        page.closeCookiesWarning();
    }

    @Step("User types email {0}")
    public void type_email(String email) {
        page.typeEmail(email);
    }

    @Step
    public void type_confirmation_email(String email) {
        page.typeConfirmEmail(email);
    }

    @Step
    public void type_password(String password) {
        page.typePassword(password);
    }

    @Step
    public void type_name(String name) {
        page.typeName(name);
    }

    @Step
    public void set_month(String month) {
        page.setMonth(month);
    }

    @Step
    public void type_day(String day) {
        page.typeDay(day);
    }

    @Step
    public void type_year(String year) {
        page.typeYear(year);
    }

    @Step
    public void set_gender(String gender) {
        page.setGender(gender);
    }

    @Step
    public void set_share(boolean value) {
        page.setShare(value);
    }

    @Step
    public void press_signup_button() {
        page.pressSignUpButton();
    }

    @Step
    public void should_see_error(String errorMessage) {
        assertThat(page.isErrorVisible(errorMessage)).as("User should see error, but he doesn't.").isTrue();
    }

    @Step
    public void should_not_see_error(String errorMessage) {
        assertThat(page.isErrorVisible(errorMessage)).as("User should not see error, but he does.").isFalse();
    }

    @Step
    public void errors_quantity_on_page_should_be (int errorsQuantity) {
        assertThat(page.getErrors()).hasSize(errorsQuantity);
    }

    @Step
    public void should_see_error_by_number(int number, String errorMessage) {
        assertThat(page.getErrorByNumber(number).getText().equals(errorMessage));
    }
}
