package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;


//Главная страница Яндекс.самокат
public class ScooterMainPage {
    private WebDriver driver;

    //Форма где вопросы и ответы, использовал для скролла и ожидания загрузки
    private By questionSection = By.className("Home_SubHeader__zwi_E");
    //Текст "Самокат на пару дней"
    private By textOnTheMainPage = By.className("Home_Header__iJKdX");
    //Логотип Яндекса
    private By yandexLogo = By.xpath(".//img[@alt='Yandex']");
    //Кнопка статус заказа
    private By orderStatusButton = By.xpath(".//button[@class='Header_Link__1TAG7']");
    //Поле для ввода номера заказа
    private By orderNumberField = By.xpath(".//input[@placeholder='Введите номер заказа']");
    //Кнопка Go
    private By goButton = By.xpath(".//button[text()='Go!']");

    public ScooterMainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Клик по кнопке "Заказать"
    public void clickOnOrderButton(String classOfButton) {
        String link = ".//button[@class='" + classOfButton + "']";
        driver.findElement(By.xpath(link)).click();
    }

    //Ожидание загрузки формы с вопросами и ответами
    public void waitForQuestionSectionloading() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(questionSection));
    }

    //Скролл до формы с вопросами и ответами
    public void scrollToQuestionSection() {
        WebElement element = driver.findElement(questionSection);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Клик по вопросу по его id
    public void clickToQuestionField(String questionId) {
        String link = "accordion__heading-" + questionId;
        driver.findElement(By.id(link)).click();
    }

    //Сравнение фактического и ожидаемого ответов
    public void compareTheAnswers(String answerId, String realAnswer) {
        String link = ".//div[@aria-labelledby='accordion__heading-" + answerId + "']/p";
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(link)));
        assertEquals("No such text", realAnswer, driver.findElement(By.xpath(link)).getText());
    }

    //Проверка на наличие текста "Самокат на пару дней"
    public void checkPresenceTextOnTheMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(textOnTheMainPage));
        assertThat(driver.findElement(textOnTheMainPage).getText(), startsWith(Constants.EXPECTED_TEXT_ON_THE_MAIN_PAGE));
    }

    //Клик по логотипу яндекса
    public void clickOnYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    //Проверка что новый открышийся сайт это Яндекс Дзен
    public void checkTheNewWindowIsYandex() {
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        String title =driver.getTitle();
        assertEquals("You are on the wrong site", Constants.EXPECTED_TITLE_OF_YANDEX_DZEN, title);
    }

    //Клип по кнопке статус заказа
    public void clickOnOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    //Заполнение поля для номера заказа
    public void enterDataToOrderNumberField(String orderNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderNumberField));
        driver.findElement(orderNumberField).sendKeys(orderNumber);
    }

    //Клик по кнопке Go
    public void clickOnGoButton() {
        driver.findElement(goButton).click();
    }

    //Весь флоу в одном тесте, создал для оптимизации
    public void checkingTheAnswerByClickingToQuestion(String questionAndAnswerId, String realAnswer) {
        waitForQuestionSectionloading();
        scrollToQuestionSection();
        clickToQuestionField(questionAndAnswerId);
        compareTheAnswers(questionAndAnswerId, realAnswer);
    }
}
