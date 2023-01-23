package day07_dropdown_jsAlerts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class C01_handleDropdownMenu {
    // ilgili ayarlari yapin
    // amazon ana sayfaya gidin
    // arama kutusu yanindaki dropdown menuden book secin
    // Books seceneginin secildigini test edin
    // Dropdown menudeki secenek sayisinin 28 oldugunu test edin
    // arama kutusuna java yazdirip arama yapin
    // title'in java icerdigini test edin

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
        driver.close();
    }

    @Test
    public void test01(){
        // amazon ana sayfaya gidin
        driver.get("https://www.amazon.com");

        // arama kutusu yanindaki dropdown menuden Books secin
        WebElement dropdownWebElementi=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select=new Select(dropdownWebElementi);
        select.selectByVisibleText("Books");

        // Books seceneginin secildigini test edin
        String expectedSecim="Books";
        String actualSecim=select.getFirstSelectedOption().getText();

        Assert.assertEquals(expectedSecim,actualSecim);

        // Dropdown menudeki secenek sayisinin 28 oldugunu test edin
        List<WebElement>optionsWebElementListesi=select.getOptions();
        int actualOptionSayisi=optionsWebElementListesi.size();
        int expectedOptionSayisi=28;

        Assert.assertEquals(expectedOptionSayisi,actualOptionSayisi);

        // arama kutusuna java yazdirip arama yapin
        WebElement aramaKutusu=driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("java" + Keys.ENTER);

        // title'in java icerdigini test edin
        String expectedTitle="java";
        String actulTitle= driver.getTitle();

        Assert.assertTrue(actulTitle.contains(expectedTitle));

    }
}
