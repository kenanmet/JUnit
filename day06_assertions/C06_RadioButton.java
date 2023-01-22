package day06_assertions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C06_RadioButton {
    // Gerekli yapiyi olusturun ve aşağıdaki görevi tamamlayın.
    //  a. Verilen web sayfasına gidin.
    //  https://facebook.com
    //  b. Cookies’i kabul edin
    //  c. Create an account buton’una basin
    //  d. Radio button elementlerini locate edin ve size uygun olani secin

    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test01() throws InterruptedException {
        //  a. Verilen web sayfasına gidin.
        //  https://facebook.com
        driver.get("https://facebook.com");

        //  b. Cookies’i kabul edin
        driver.findElement(By.xpath("//button[@title='Sadece temel çerezlere izin ver']")).click();

        //  c. Create an account buton’una basin
        driver.findElement(By.xpath("//*[text()='Yeni Hesap Oluştur']")).click();

        //  d. Radio button elementlerini locate edin ve size uygun olani secin
        driver.findElement(By.xpath("//*[text()='Erkek']")).click();

        Thread.sleep(2000);

    }
}
