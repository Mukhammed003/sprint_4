package praktikum.windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

//Окно "Заказ оформлен"
public class OrderPlacedWindow {
    private WebDriver driver;

    //Само окно
    private By orderPlacedWindow = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    public OrderPlacedWindow(WebDriver driver) {
        this.driver = driver;
    }

    //Проверка на наличие текста "Заказ оформлен"
    public void checkPresenceMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPlacedWindow));
        assertThat(driver.findElement(orderPlacedWindow).getText(), startsWith(Constants.EXPECTED_TEXT_AFTER_ORDER));
    }
}
