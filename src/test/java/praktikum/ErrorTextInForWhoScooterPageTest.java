package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.ForWhoScooterPage;
import praktikum.pages.ScooterMainPage;

@RunWith(Parameterized.class)
public class ErrorTextInForWhoScooterPageTest {
    WebDriver driver;

    private final String classOfButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;


    public ErrorTextInForWhoScooterPageTest(String classOfButton, String name, String surname, String address, String phoneNumber) {
        this.classOfButton = classOfButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Rule
    public DriverRule factory = new DriverRule();

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Button_Button__ra12g", "Cristiano", "Ronaldo", "Puerto-Rico", "8463736"},
                {"Button_Button__ra12g Button_Middle__1CSJM", "Лионель3", "Месси5", "Орал/", "84637367777777"},
                {"Button_Button__ra12g", "Неймар$", "Джуниор^", "Алматы*", "84637367777п"},
        };
    }

    @Before
    public void initializeDriver() {
        driver = factory.getDriver();
    }

    @Test
    public void correctnessOfErrorTextInForWhoScooterPage() {
        ScooterMainPage scooterMainPage = new ScooterMainPage(driver);
        scooterMainPage.clickOnOrderButton(classOfButton);

        ForWhoScooterPage forWhoScooterPage = new ForWhoScooterPage(driver);
        forWhoScooterPage.waitForForWhoScooterFormLoading();
        forWhoScooterPage.enterDataToNameField(name);
        forWhoScooterPage.enterDataToSurnameField(surname);
        forWhoScooterPage.enterDataToAddressField(address);
        forWhoScooterPage.enterDataToPhoneNumberField(phoneNumber);
        forWhoScooterPage.clickToNextButton();
        forWhoScooterPage.checkingCorrectnessOfErrorTexts();
    }
}
