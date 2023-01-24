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

public class C03_WindowHandle {
    // 1- https://the-internet.herokuapp.com/iframe adresine gidin
    // 2- elemental selenium linkini tiklayin
    // 3- yeni tab'a gecip sayfadaki en buyuk yazinin "Elemental Selenium" oldugunu test edin
    // 4- ilk sayfaya geri donup sayfadaki yazinin
    //    "An iFrame containing the TinyMCE WYSIWYG Editor" oldugunu test edin

    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown(){
        //driver.close();
    }

    @Test
    public void test01(){
        // 1- https://the-internet.herokuapp.com/iframe adresine gidin
        driver.get("https://the-internet.herokuapp.com/iframe");
        String ilkSayfaWHD=driver.getWindowHandle();

        // 2- elemental selenium linkini tiklayin
        driver.findElement(By.xpath("//*[text()='Elemental Selenium']")).click();

        // 3- yeni tab'a gecip sayfadaki en buyuk yazinin "Elemental Selenium" oldugunu test edin
        Set<String> tumSayfalarinWHD=driver.getWindowHandles();
        String ikinciSayfaWHD="";

        for (String eachWdh:tumSayfalarinWHD
             ) {
            if (!eachWdh.equals(ilkSayfaWHD)){
                ikinciSayfaWHD=eachWdh;
            }
        }
        driver.switchTo().window(ikinciSayfaWHD);
        String actualEnBuyukYazi=driver.findElement(By.tagName("h1")).getText();
        String expectedEnBuyukYazi="Elemental Selenium";

        Assert.assertEquals(expectedEnBuyukYazi,actualEnBuyukYazi);

        // 4- ilk sayfaya geri donup sayfadaki yazinin
        //    "An iFrame containing the TinyMCE WYSIWYG Editor" oldugunu test edin
        driver.switchTo().window(ilkSayfaWHD);
        String actualSayfaYazisi=driver.findElement(By.tagName("h3")).getText();
        String expectedSayfaYazisi="An iFrame containing the TinyMCE WYSIWYG Editor";

        Assert.assertEquals(expectedSayfaYazisi,actualSayfaYazisi);
    }
}
