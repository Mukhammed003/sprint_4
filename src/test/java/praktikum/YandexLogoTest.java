package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.ScooterMainPage;

//Тест для проверки правильности работы логотипа яндекса
public class YandexLogoTest {
    WebDriver driver;

    //Создаем объект класса DriverRule, для дальнейшего выбора браузера
    @Rule
    public DriverRule factory = new DriverRule();

    //Получаем созданный драйвер с опциями
    @Before
    public void initializeDriver() {
        driver = factory.getDriver();
    }

    //Сам тест
    @Test
    public void theCorrectOperationOfTheYandexLogo() {
        //Объект страницы ScooterMainPage
        ScooterMainPage scooterMainPage = new ScooterMainPage(driver);
        //Клик по логотипу яндекса
        scooterMainPage.clickOnYandexLogo();
        //Проверка что открывшейся новый сайт это Яндекс Дзен
        scooterMainPage.checkTheNewWindowIsYandex();
    }
}
