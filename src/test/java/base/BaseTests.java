package base;

import com.applitools.eyes.selenium.Eyes;
import components.LoginComponent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class BaseTests {

    protected static WebDriver driver;
    protected static Eyes eyes = new Eyes();

    @BeforeClass
    public static void launchApplication() {
        Properties props = System.getProperties();
        try {
            props.load(
                    new FileInputStream(new File("resources/test.properties")));
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        driver = new ChromeDriver();
        driver.get(System.getProperty("app.url"));

        new LoginComponent(driver).login();

        eyes.setApiKey(System.getProperty("applitools.api.key"));
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
        eyes.close();
    }

    public void validateWindow(){
        eyes.open(driver, "Parabank",
                Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkWindow();
    }
}
