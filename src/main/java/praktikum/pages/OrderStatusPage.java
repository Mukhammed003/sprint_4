package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class OrderStatusPage {
    WebDriver driver;

    private By notFoundImage = By.xpath(".//img[@alt='Not found']");

    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
    }

    public void presenceOfPicture() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImage));
        assertTrue(driver.findElement(notFoundImage).isDisplayed());
    }
}
