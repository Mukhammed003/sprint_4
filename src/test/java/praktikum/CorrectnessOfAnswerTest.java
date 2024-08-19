package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.ScooterMainPage;
//Тесты для проверки правильности ответа при клике на вопрос
@RunWith(Parameterized.class)
public class CorrectnessOfAnswerTest {
    WebDriver driver;

    //Определил все нужные поля для параметризации
    private final String fieldId;
    private final String expectedAnswer;

    //Конструктор для полей
    public CorrectnessOfAnswerTest(String fieldId, String expectedAnswer) {
        this.fieldId = fieldId;
        this.expectedAnswer = expectedAnswer;
    }

    //Создаем объект класса DriverRule, для дальнейшего выбора браузера
    @Rule
    public DriverRule factory = new DriverRule();

    //Сами данные для теста
        @Parameterized.Parameters
        public static Object[][] getData() {
            return new Object[][] {
                    { "0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    { "1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    { "2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    { "3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    { "4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    { "5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    { "6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    { "7", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
            };
        }

    //Получаем созданный драйвер с опциями
    @Before
    public void initializeDriver() {
        driver = factory.getDriver();
    }

    //Сам тест
    @Test
    public void correctnessOfTheAnswersInQuestionAnswerSectionTest() {
        //Объект страницы ScooterMainPage
        ScooterMainPage scooterMainPage = new ScooterMainPage(driver);
        //Одним методом кликаем и проверям правильность ответа на вопрос
        scooterMainPage.checkingTheAnswerByClickingToQuestion(fieldId, expectedAnswer);
    }
}
