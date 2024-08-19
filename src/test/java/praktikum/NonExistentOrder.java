package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.OrderStatusPage;
import praktikum.pages.ScooterMainPage;

public class NonExistentOrder {
    WebDriver driver;

    @Rule
    public DriverRule factory = new DriverRule();

    @Before
    public void initializeDriver() {
        driver = factory.getDriver();
    }

    @Test
    public void presenceOfPictureWhenEnteringNonExistentOder() {
        ScooterMainPage scooterMainPage = new ScooterMainPage(driver);
        scooterMainPage.clickOnOrderStatusButton();
        scooterMainPage.enterDataToOrderNumberField("123");
        scooterMainPage.clickOnGoButton();

        OrderStatusPage orderStatusPage =new OrderStatusPage(driver);
        orderStatusPage.presenceOfPicture();
    }
}
