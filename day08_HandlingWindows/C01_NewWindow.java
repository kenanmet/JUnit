package day08_HandlingWindows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_NewWindow {
    /*
        Selenium 4 ile windows konusunda yeni bir ozellik geldi

        Istersek kontrollu olarak driver icin yeni bir window veya tab olusturabiliriz
        Bu durumda driver'imiz da otomatik olarak yeni sayfaya gecmis olur

        Testin ilerleyen asamalarinda yeniden amazon sayfasina donmek gerekiyorsa
        amazon sayfasindayken bu window'un window handle degerini alip kaydetmeliyiz
        ve o sayfaya gecmek istendiginde
        driver.switchTo().window(istenenSayfaninWindowHandleDegeri);
        kodu ilesayfaya gecis yapilir
     */
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
        driver.quit();
    }

    @Test
    public void test01() throws InterruptedException {
        // amazon.com'a gidin
        driver.get("https://www.amazon.com");
        Thread.sleep(3000);

        // Testin ilerleyen asamalarinda yeniden amazon sayfasina donmek gerekiyorsa
        // amazon sayfasindayken bu window'un window handle degerini alip kaydetmeliyiz
        String ilkSayfaHandleDegeri=driver.getWindowHandle();

        // yeni bir tab'da wisequarter.com'a gidin ve gittiginizi test edin
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.wisequarter.com");

        String actualUrl= driver.getCurrentUrl();
        String expectedUrl="wisequarter";

        Assert.assertTrue(actualUrl.contains(expectedUrl));

        // wisequarter testini yaptiktan sonra
        // yeniden amazon'un acik oldugu sayfaya gecin ve
        // amazon ana sayfasinin acik oldugunu test edin
        driver.switchTo().window(ilkSayfaHandleDegeri);
        Thread.sleep(3000);
        actualUrl=driver.getCurrentUrl();
        expectedUrl="amazon";

        Assert.assertTrue(actualUrl.contains(expectedUrl));
    }
}
