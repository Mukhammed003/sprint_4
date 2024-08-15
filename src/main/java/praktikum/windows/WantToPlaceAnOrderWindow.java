package praktikum.windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

import java.time.Duration;

//Окно "Хотите оформитьь заказ"
public class WantToPlaceAnOrderWindow {
    private WebDriver driver;

    //Само окно
    private By wantToPlaceAnOrderWindow = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    //Кнопка "Да"
    private By yesButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    public WantToPlaceAnOrderWindow(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание загрузки окна
    public void waitWantToPlaceAnOrderWindowLoading() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(wantToPlaceAnOrderWindow));
    }

    //Клик по кнопке "Да"
    public void clickToYesButton() {
        driver.findElement(yesButton).click();
    }

}
