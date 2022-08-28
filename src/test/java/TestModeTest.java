import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class TestModeTest {

    private WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp1() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = DataGenerator.RegistrationDto.generateUser("active");
        $("input[name='login']").val(DataGenerator.generateLogin());
        $("input[name='password']").val(DataGenerator.generatePassword());
        $(".button__text").click();
        $x("//h2[contains(text(), 'Личный кабинет')]").shouldHave(Condition.exactText("Личный кабинет"),
                Duration.ofSeconds(15));

    }
}
