package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Constants;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

//Главная страница Яндекс.самокат
public class ScooterMainPage {
    private WebDriver driver;

    //Форма где вопросы и ответы, использовал для скролла и ожидания загрузки
    private By questionSection = By.className("Home_SubHeader__zwi_E");

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

    //Весь флоу в одном тесте, создал для оптимизации
    public void checkingTheAnswerByClickingToQuestion(String questionAndAnswerId, String realAnswer) {
        waitForQuestionSectionloading();
        scrollToQuestionSection();
        clickToQuestionField(questionAndAnswerId);
        compareTheAnswers(questionAndAnswerId, realAnswer);
    }
}
