package day07_dropdown_jsAlerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_JsAlerts {
    // Gerekli ayarlamalari yapip
    // https://the-internet.herokuapp.com/javascript_alerts adresine gidin
    // 3 test methodu olusturup her method'da bir JsAlert'e basin
    // ilgili methodlari kullanin

    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @AfterClass
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2500);
        driver.close();
    }

    @Test
    public void test01() throws InterruptedException {
        // 1.alert'e tiklayalim
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
        Thread.sleep(2000);

        // alert'deki yazinin "I am a JS Alert" oldugunu test edelim
        String actualAlertYazisi=driver.switchTo().alert().getText();
        String expectedAlertYazisi="I am a JS Alert";

        Assert.assertEquals(expectedAlertYazisi,actualAlertYazisi);

        // OK'a tiklayip alert'i kapatalim
        driver.switchTo().alert().accept();
    }

    @Test
    public void test02() throws InterruptedException {
        // 2.alert'e tiklayalim
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
        Thread.sleep(2000);

        // cancel'e tiklayip alert'i kapatalim ve cikan sonuc yazisinin "You clicked: Cancel" oldugunu test edelim
        driver.switchTo().alert().dismiss();
        String actualSonucYazisi=driver.findElement(By.xpath("//*[text()='You clicked: Cancel']")).getText();
        String expectedSonucYazisi="You clicked: Cancel";

        Assert.assertEquals(expectedSonucYazisi,actualSonucYazisi);
    }

    @Test
    public void test03() throws InterruptedException {
        // 3.alert'e tiklayalim
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
        Thread.sleep(2000);

        // cikan propt ekranina "kenan" yazdiralim ve OK'a basarak alert'i kapatalim
        driver.switchTo().alert().sendKeys("kenan");
        driver.switchTo().alert().accept();

        // cikan sonuc yazisinin kenan icerdigini test edelim
        String actualSonucYazisi=driver.findElement(By.xpath("//*[@id='result']")).getText();
        String expectedSonucYazisi="kenan";

        Assert.assertTrue(actualSonucYazisi.contains(expectedSonucYazisi));
    }
}
