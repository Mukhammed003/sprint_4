package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

import java.time.Duration;

//Форма "Для кого самокат"
public class ForWhoScooterPage {
    private WebDriver driver;

    //Сама форма
    private By forWhoScooterForm = By.xpath(".//div[text()='Для кого самокат']");

    //Поле имя
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Поле фамилия
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле адрес
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле станция метро
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле номер телефона
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private By nextButton = By.xpath(".//button[text()='Далее']");

    public ForWhoScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание загрузки самой страницы
    public void waitForForWhoScooterFormLoading() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(forWhoScooterForm));
    }

    //Заполнение поля имя
    public void enterDataToNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //Заполнение поля фамилия
    public void enterDataToSurnameField(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    //Заполнение поля адрес
    public void enterDataToAddressField(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //Заполнение поля станция метро
    public void chooseTheMetroStation(String metroStationId) {
        driver.findElement(metroStationField).click();
        String link = ".//li[@data-index='" + metroStationId + "']";
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(link)));
        driver.findElement(By.xpath(link)).click();
    }

    //Заполнение поля номер телефона
    public void enterDataToPhoneNumberField(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    //Клик по кнопке "Далее"
    public void clickToNextButton() {
        driver.findElement(nextButton).click();
    }

    //Все действия на этой форме, создал для оптимизации
    public void fillingInTheFieldsForWhoScooterForm(String name, String surname, String address, String metroStationId, String phoneNumber) {
        waitForForWhoScooterFormLoading();
        enterDataToNameField(name);
        enterDataToSurnameField(surname);
        enterDataToAddressField(address);
        chooseTheMetroStation(metroStationId);
        enterDataToPhoneNumberField(phoneNumber);
        clickToNextButton();
    }

}
