import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.SignUpSteps;

@RunWith(SerenityRunner.class)
public class WhenSignUp {

    @Steps
    SignUpSteps steps;

    @Managed(driver = "firefox")
    WebDriver driver;

    @Test
    @Pending
    public void typeInvalidYear() {
        steps.open_signup_page();
        steps.close_cookies_warning();
        steps.set_month("May");
        steps.type_day("10");
        steps.type_year("85");
        steps.set_share(true);
        steps.press_signup_button();
        steps.should_see_error("Enter a valid year.");
        steps.should_not_see_error("Select your birth month.");
        steps.errors_quantity_on_page_should_be(7);
    }

    @Test
    public void confirmInvalidEmail() {
        steps.open_signup_page();
        steps.close_cookies_warning();
        steps.type_email("test@mail.test");
        steps.type_confirmation_email("wrong@mail.test");
        steps.type_name("TestName");
        steps.set_gender("Male");
        steps.press_signup_button();
        steps.errors_quantity_on_page_should_be(6);
        steps.should_see_error("The email addresses don't match.");
    }

    @Test
    public void signUpWithEmptyPassword() {
        steps.open_signup_page();
        steps.close_cookies_warning();
        steps.type_email("test@mail.test");
        steps.type_confirmation_email("test@mail.test");
        steps.type_name("TestName");
        steps.press_signup_button();
        steps.errors_quantity_on_page_should_be(6);
        steps.should_see_error("You need to enter a password.");
    }

    @Test
    public void typeInvalidValues() {
        steps.open_signup_page();
        steps.close_cookies_warning();
        steps.type_email("testmail@gtrg.tv");
        steps.type_confirmation_email("wrong@test.mail");
        steps.type_password("");
        steps.type_name("");
        steps.type_day("");
        steps.type_year("");
        steps.set_share(true);
        steps.press_signup_button();
        steps.errors_quantity_on_page_should_be(8);
        steps.should_see_error_by_number(8, "Confirm you're not a robot.");
    }
}
