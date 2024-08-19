package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

import java.time.Duration;

//Форма "Про аренду"
public class AboutRentPage {
    private WebDriver driver;

    //Сама форма
    private By aboutRentForm = By.xpath(".//div[text()='Про аренду']");

    //Поле дата
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле срока аренды
    private By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder']");
    //Поле комментарий
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать"
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");


    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание загрузки формы
    public void waitForAboutRentFormLoading() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(aboutRentForm));
    }

    //Выбор даты
    public void chooseTheDate(String date) {
        driver.findElement(dateField).click();
        String link = ".//div[text()='" + date + "']";
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(link)));
        driver.findElement(By.xpath(link)).click();
    }

    //Выбор срока аренды
    public void chooseRentalPeriod(String period) {
        driver.findElement(rentalPeriodField).click();
        String link = ".//div[text()='" + period + "']";
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(link)));
        driver.findElement(By.xpath(link)).click();
    }

    //Выбор цвета
    public void chooseTheColourOfScooter(String colour) {
        driver.findElement(By.id(colour)).click();
    }

    //Заполнение поля комментарий
    public void enterTheComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    //Клик по кнопке "Заказать"
    public void clickToOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Все действия на этой форме, создал для оптимизации
    public void fillingInTheFieldsAboutRentForm(String date, String period, String colour, String comment) {
        waitForAboutRentFormLoading();
        chooseTheDate(date);
        chooseRentalPeriod(period);
        chooseTheColourOfScooter(colour);
        enterTheComment(comment);
        clickToOrderButton();
    }

}
