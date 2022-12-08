package web.basic;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    protected WebDriver webDriver;

    @BeforeMethod
    public void startBrowser() {
        WebDriverManager.chromedriver().setup();
        webDriver=new ChromeDriver(new ChromeOptions().setHeadless(true));
        open();
        webDriver = Selenide.webdriver().driver().getWebDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        webDriver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown() {
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWindow();
    }

}
