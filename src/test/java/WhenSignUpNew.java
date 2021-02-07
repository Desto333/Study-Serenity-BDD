import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.SignUpSteps;

@RunWith(SerenityRunner.class)
public class WhenSignUpNew {

    @Steps
    SignUpSteps steps;

    @Managed(driver = "firefox")
    WebDriver driver;

    @Test
    @Title("User enters invalid year")
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
        steps.should_see_error("It does not exist.");
    }
}
