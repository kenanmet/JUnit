package day08_HandlingWindows;

import org.junit.Assert;
import org.junit.Test;
import utilities.ReusableMethods;
import utilities.TestBase;

public class C04_TestBaseIlkTest extends TestBase {
    @Test
    public void test01(){
        // amazon.com'a gidin
        driver.get("https://www.amazon.com");
        ReusableMethods.bekle(3);

        // amazon'a gittiginizi test edin
        String actualUrl=driver.getCurrentUrl();
        String expectedKelime="amazon";

        Assert.assertTrue(actualUrl.contains(expectedKelime));
    }
}
