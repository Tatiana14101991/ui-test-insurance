
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import javax.swing.plaf.synth.Region;
import java.util.concurrent.TimeUnit;


/**
 * Created by Tanya
 */
public class InsuranceTest {
    WebDriver driver;
    String baseUrl;

    @Before
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        baseUrl = "http://www.rgs.ru/";
        driver = new ChromeDriver();
        // driver.manage().timeouts().implicitlyWait( 30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }


    @Test
    public void testInsurance() throws InterruptedException {
        driver.findElement(By.xpath("//ol[contains(@class,'rgs-menu')]/li/a[contains(text(),'Страхование')]")).click();
        driver.findElement(By.xpath("//*[contains(@class,'rgs-main-menu-category')]//a[contains(text() ,'ДМС')]")).click();

        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

        WebElement sendBtn = driver.findElement(By.xpath("//a[contains(text(),'Отправить заявку')]"));
        wait.until(ExpectedConditions.visibilityOf(sendBtn)).click();

        WebElement title = driver.findElement(By.xpath("//h4[@class='modal-title'] "));
        wait.until(ExpectedConditions.visibilityOf(title));


        Assert.assertEquals("Заявка на добровольное медицинское страхование", title.getText());

        fillField(By.name("LastName"), "Иванов");
        fillField(By.name("FirstName"), "Иван");
        fillField(By.name("MiddleName"), "Иванович");

        new Select(driver.findElement(By.name("Region"))).selectByVisibleText("Москва");

        fillField(By.name("Comment"), "Autotest)");
        fillField(By.name("Email"), "432932326");
        //  fillField(By.name("Email"), "432932326");
        driver.findElement(By.xpath("//input[@class='checkbox']")).click();
        driver.findElement(By.id("button-m")).click();

        Thread.sleep(3000);
      //  String mail = driver.findElement(By.xpath("//div[@class='row']//*[contains(text(),'почты')]")).getText();
       // System.out.println(mail);


        Assert.assertEquals("Иванов",
                driver.findElement(By.name("LastName")).getAttribute("value"));
        Assert.assertEquals("Иван",
                driver.findElement(By.name("FirstName")).getAttribute("value"));
        Assert.assertEquals("Иванович",
                driver.findElement(By.name("MiddleName")).getAttribute("value"));
        Assert.assertEquals("Введите номер телефона", driver.findElement(By.xpath("//div[@class='row']//*[contains(text(),'телефона')]")).getText());

    }


    public void fillField(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }


    @After
    public void afterTest() {
        driver.quit();

        {
        }
    }
}
