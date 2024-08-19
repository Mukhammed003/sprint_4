package praktikum;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.pages.AboutRentPage;
import praktikum.pages.ForWhoScooterPage;
import praktikum.pages.ScooterMainPage;
import praktikum.windows.OrderPlacedWindow;
import praktikum.windows.WantToPlaceAnOrderWindow;

//Тесты для заказа самоката
@RunWith(Parameterized.class)
public class OrderTest {
    WebDriver driver;
    //Определил все нужные поля для параметризации
    private final String classOfOrderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;

    //Конструктор для полей
    public OrderTest(String classOfOrderButton, String name, String surname, String address, String metroStation, String phoneNumber, String date, String rentalPeriod, String colour, String comment) {
        this.classOfOrderButton = classOfOrderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    //Создаем объект класса DriverRule, для дальнейшего выбора браузера
    @Rule
    public DriverRule factory = new DriverRule();

    //Сами данные для теста
    @Parameterized.Parameters
    public static Object[][] getData() {
       return new Object[][] {
                {"Button_Button__ra12g", "Криштиану", "Роналду", "Пушкинская 122", "6", "87777777777", "21", "пятеро суток", "black", "Нет комментов"},
                {"Button_Button__ra12g Button_Middle__1CSJM", "Лионель", "Месси", "Абая 157", "1", "86666666666", "25", "трое суток", "grey", "Есть комменты"},
        };
    }

    //Получаем созданный драйвер с опциями
    @Before
    public void initializeDriver() {
        driver = factory.getDriver();
    }

    //Сам тест
    @Test
    public void theFullFlowOfOrderingAScooter() {
        //Объект страницы ScooterMainPage
        ScooterMainPage scooterMainPage = new ScooterMainPage(driver);
        //Сначала кликаем по кнопке "Заказать"
        scooterMainPage.clickOnOrderButton(classOfOrderButton);

        //Объект страницы ForWhoScooterPage
        ForWhoScooterPage forWhoScooterPage = new ForWhoScooterPage(driver);
        //Одним методом заполняем все поля на этой форме
        forWhoScooterPage.fillingInTheFieldsForWhoScooterForm(name, surname, address, metroStation, phoneNumber);

        //Объект страницы AboutRentPage
        AboutRentPage aboutRentPage = new AboutRentPage(driver);
        //Одним методом заполняем все поля на этой форме
        aboutRentPage.fillingInTheFieldsAboutRentForm(date, rentalPeriod, colour, comment);

        //Объект окна WantToPlaceAnOrderWindow
        WantToPlaceAnOrderWindow wantToPlaceAnOrderWindow = new WantToPlaceAnOrderWindow(driver);
        //Ожидание загрузки окна
        wantToPlaceAnOrderWindow.waitWantToPlaceAnOrderWindowLoading();
        //Кликаем по кнопке "Да"
        wantToPlaceAnOrderWindow.clickToYesButton();

        //Объект окна OrderPlacedWindow
        OrderPlacedWindow orderPlacedWindow = new OrderPlacedWindow(driver);
        //Проверяем на наличие текста "Заказ оформлен"
        orderPlacedWindow.checkPresenceMessage();
    }
}
