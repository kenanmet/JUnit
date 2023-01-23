package day07_dropdown_jsAlerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C04_IFrame {
    // 1 ) https://the-internet.herokuapp.com/iframe adresine gidin.
    // 2 ) Bir metod olusturun: iframeTest
    //  - “An IFrame containing….” textinin erisilebilir oldugunu test edin ve konsolda yazdirin.
    //  - Text Box’a “Merhaba Dunya!” yazin.
    //  - TextBox’in altinda bulunan “Elemental Selenium” linkini textinin
    //    gorunur oldugunu dogrulayin ve  konsolda yazdirin.

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
        // 1 ) https://the-internet.herokuapp.com/iframe adresine gidin.
        driver.get("https://the-internet.herokuapp.com/iframe");

        // 2 ) Bir metod olusturun: iframeTest
        //  - “An IFrame containing….” textinin erisilebilir oldugunu
        //     test edin ve konsolda yazdirin.
        WebElement actualYaziElementi=driver.findElement(By.tagName("h3"));

        Assert.assertTrue(actualYaziElementi.isEnabled());

        //  - Text Box’a “Merhaba Dunya!” yazin.
        /*
        Burada aradigimiz webelement bir iFrame icerisinde oldugu icin once
        o iframe'e switchTo yapmaliyiz
        */
        WebElement iframeWebElementi= driver.findElement(By.xpath("//*[@id='mce_0_ifr']"));
        driver.switchTo().frame(iframeWebElementi);
        WebElement textBoxElementi=driver.findElement(By.xpath("//body[@id='tinymce']"));
        textBoxElementi.clear();
        textBoxElementi.sendKeys("Merhaba Dunya!");
        Thread.sleep(5000);

        //  - TextBox’in altinda bulunan “Elemental Selenium” linkinin
        //    gorunur oldugunu dogrulayin ve  konsolda yazdirin.
        /*
        IFrame'in icine girdikten sonra, oradan cik denilinceye kadar
        driver iframe'in icinde kalir

        eger disina cikmak istersek;
        driver.switchTo().parentFrame(); // bulundugu iframe'den bir ust html sayfasina gecer
        bu daha cok ic ice iframe'ler oldugunda tercih edilir
         */
        driver.switchTo().defaultContent(); // ana sayfaya gecis yapar
        WebElement elementSeleniumLinkElementi=driver.findElement(By.xpath("//*[text()='Elemental Selenium']"));

        Assert.assertTrue(elementSeleniumLinkElementi.isDisplayed());
        System.out.println(elementSeleniumLinkElementi.getText());
    }
}
