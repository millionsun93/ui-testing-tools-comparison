import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by milli on 04-Jan-17.
 */
public class SignInTest {
    AndroidDriver driver;
    String fileName = "app-debug.apk";
    File boilerplateFile = new File("src/main/resources/" + fileName);

    AndroidDriver driver;
    String fileName = "boilerplate.apk";
    File boilerplateFile = new File("src/main/resources/" + fileName);

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("app", boilerplateFile);
        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "G935F");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void testSignInWithCorrectAccount() throws Exception {
        System.out.print(driver.currentActivity());
        driver.findElementById("edt_first_name").sendKeys("Ethan");
        driver.findElementById("edt_last_name").sendKeys("Le");
        driver.findElementById("edt_email_address").sendKeys("ethan.le@innovatube.com");
        driver.findElementById("edt_password").sendKeys("123456");
        driver.findElementById("edt_confirm_password").sendKeys("123456");
        driver.navigate().back();
        driver.findElementById("edt_dob").click();
        driver.findElement(By.xpath("//android.widget.Button[contains(@text, 'OK')]")).click();
        driver.findElementById("edt_promotion_code").sendKeys("123456");
        driver.navigate().back();
        driver.findElementById("btn_join").click();
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, 'Hello World!')]")));

    }
    @After
    public void tearDown() throws Exception {
        driver.quit();

    }
}
