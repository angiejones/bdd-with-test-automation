package utils;

import org.openqa.selenium.WebDriver;
import pages.AccountActivityPage;

public class NavigationUtils {

    public static AccountActivityPage goToAccountActivity(WebDriver driver, int accountId){
        driver.get(System.getProperty("app.url") + "activity.htm?id=" + accountId);
        return new AccountActivityPage(driver);
    }
}
