package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

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
    //Логотип скутера
    private By scooterLogo = By.xpath(".//img[@alt='Scooter']");
    //Текст ошибки у поля имя
    private By errorTextOfNameField = By.xpath(".//div[text()='Введите корректное имя']");
    //Текст ошибки у поля фамилия
    private By errorTextOfSurnameField = By.xpath(".//div[text()='Введите корректную фамилию']");
    //Текст ошибки у поля адрес
    private By errorTextOfAddressField = By.xpath(".//div[text()='Введите корректный адрес']");
    //Текст ошибки у поля станция метро
    private By errorTextOfMetroStationField = By.xpath(".//div[text()='Выберите станцию']");
    //Текст ошибки у поля номер телефона
    private By errorTextOfPhoneNumberField = By.xpath(".//div[text()='Введите корректный номер']");

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

    //Клик по логотипу скутера
    public void clickOnScooterLogo() {
        driver.findElement(scooterLogo).click();
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

    public void checkingCorrectnessOfErrorTexts() {
        assertEquals("Another error text on name field", Constants.EXPECTED_ERROR_MESSAGE_FOR_NAME_FIELD, driver.findElement(errorTextOfNameField).getText());
        assertEquals("Another error text on surname field", Constants.EXPECTED_ERROR_MESSAGE_FOR_SURNAME_FIELD, driver.findElement(errorTextOfSurnameField).getText());
        assertEquals("Another error text on address field", Constants.EXPECTED_ERROR_MESSAGE_FOR_ADDRESS_FIELD, driver.findElement(errorTextOfAddressField).getText());
        assertEquals("Another error text on metro station field", Constants.EXPECTED_ERROR_MESSAGE_FOR_METRO_STATION_FIELD, driver.findElement(errorTextOfMetroStationField).getText());
        assertEquals("Another error text on phone number field", Constants.EXPECTED_ERROR_MESSAGE_FOR_PHONE_NUMBER_FIELD, driver.findElement(errorTextOfPhoneNumberField).getText());
    }
}
