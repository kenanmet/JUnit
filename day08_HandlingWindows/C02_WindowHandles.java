package day08_HandlingWindows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class C02_WindowHandles {
    // ● https://the-internet.herokuapp.com/windows adresine gidin.
    // ● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
    // ● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
    // ● Click Here butonuna basın.
    // ● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
    // ● Sayfadaki textin “New Window” olduğunu doğrulayın.
    // ● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.

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
        // ● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");
        String ilkSayfaWindowHandleDegeri= driver.getWindowHandle();

        // ● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        String actualTextYazisi=driver.findElement(By.tagName("h3")).getText();
        String expectedTextYazisi="Opening a new window";

        Assert.assertEquals(expectedTextYazisi,actualTextYazisi);

        // ● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String actualTitle= driver.getTitle();
        String expectedTitle="The Internet";

        Assert.assertEquals(expectedTitle,actualTitle);

        // ● Click Here butonuna basın.
        driver.findElement(By.xpath("//*[text()='Click Here']")).click();
        Thread.sleep(3000);
        /*
            Kontrolsuz acilan tab'a gecis yapmak icin
            1- ilk sayfada iken o sayfanin WHD alip kaydedin
            2- 2.sayfa acildiktan sonra getWindowHandles() kullanarak
               acik olan tum sayfalarin WH degerlerini bir set olarak kaydedin
            3- Su anda elimizde 2 elementli bir Set var
               elementlerden bir tanesi 1.sayfanin WHD
               1.sayfanin WHD'ine esit olmayan ise 2.sayfanin WHD olur
         */
        Set<String>tumWHDegerleriSeti=driver.getWindowHandles();

        String ikinciSayfaWHD="";
        for (String eachWhd: tumWHDegerleriSeti
             ) {
            if (!eachWhd.equals(ilkSayfaWindowHandleDegeri)){
                ikinciSayfaWHD=eachWhd;
            }
        }

        // ● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        driver.switchTo().window(ikinciSayfaWHD);
        actualTitle=driver.getTitle();
        expectedTitle="New Window";

        Assert.assertEquals(expectedTitle,actualTitle);

        // ● Sayfadaki textin “New Window” olduğunu doğrulayın.
        actualTextYazisi=driver.findElement(By.tagName("h3")).getText();
        expectedTextYazisi="New Window";

        Assert.assertEquals(expectedTextYazisi,actualTextYazisi);

        // ● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(ilkSayfaWindowHandleDegeri);
        actualTitle=driver.getTitle();
        expectedTitle="The Internet";

        Assert.assertEquals(expectedTitle,actualTitle);
    }
}
