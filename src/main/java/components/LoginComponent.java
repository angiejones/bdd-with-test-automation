package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginComponent {

    private WebDriver driver;

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton =
            By.xpath("//div[@id='loginPanel']/descendant::input[@type='submit']");

    public LoginComponent(WebDriver driver){
        this.driver = driver;
    }

    public void login(){
        login(System.getProperty("customer.username"),
                System.getProperty("customer.password"));
    }

    public void login(String username, String password){
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
