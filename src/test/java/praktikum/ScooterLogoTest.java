package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.ForWhoScooterPage;
import praktikum.pages.ScooterMainPage;

//Тест для проверки правильности работы логотипа скутера
@RunWith(Parameterized.class)
public class ScooterLogoTest {
    WebDriver driver;

    //Определил все нужные поля для параметризации
    private final String classOfbutton;

    //Конструктор для полей
    public ScooterLogoTest(String classOfbutton) {
        this.classOfbutton = classOfbutton;
    }

    //Создаем объект класса DriverRule, для дальнейшего выбора браузера
    @Rule
    public DriverRule factory = new DriverRule();

    //Сами данные для теста
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"Button_Button__ra12g"},
                {"Button_Button__ra12g Button_Middle__1CSJM"},
        };
    }

    //Получаем созданный драйвер с опциями
    @Before
    public void initializeDriver() {
        driver = factory.getDriver();
    }

    //Сам тест
    @Test
    public void theCorrectOperationOfTheScooterLogo() {
        //Объект страницы ScooterMainPage
        ScooterMainPage scooterMainPage = new ScooterMainPage(driver);
        //Сначала кликаем по кнопке "Заказать"
        scooterMainPage.clickOnOrderButton(classOfbutton);

        //Объект страницы ForWhoScooterPage
        ForWhoScooterPage forWhoScooterPage = new ForWhoScooterPage(driver);
        //Клик по логотипу скутера
        forWhoScooterPage.clickOnScooterLogo();

        //Проверяем на наличие текста на главной странице
        scooterMainPage.checkPresenceTextOnTheMainPage();
    }
}
