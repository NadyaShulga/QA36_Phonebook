package manager;
import model.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {

        super(wd);
    }

    public void openLoginRegistrationForm(){
       // click(By.cssSelector("a[href='/l']"));
        click(By.cssSelector("a[href='/login']"));
    }
    public void fillLoginRegistrationForm(User user) {
        //for email
        type(By.cssSelector("input[placeholder='Email']"), user.getEmail());

        //for password
        type(By.cssSelector("input[placeholder='Password']"), user.getPassword());
    }
    public void fillLoginRegistrationForm(String email, String password) {
        //for email
        type(By.cssSelector("input[placeholder='Email']"), email);

        //for password
        type(By.cssSelector("input[placeholder='Password']"), password);
    }
    public void submitLogin(){
        click(By.cssSelector("button[name='login']"));
    }

    public boolean isLogged() {
      //return  wd.findElement(By.xpath("//*[text()='Sign Out']")).isDisplayed();
        List<WebElement> list = wd.findElements(By.xpath("//*[text()='Sign Out']"));
        return list.size() > 0;
    }

    public void logout() {

        click(By.xpath("//*[text()='Sign Out']"));
    }

    public boolean isErrorMessageDisplayed(String message) {
     //  Alert alert = wd.switchTo().alert();
       Alert alert =  new WebDriverWait(wd,Duration.ofSeconds(9)).until(ExpectedConditions.alertIsPresent());

       //wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       String text = alert.getText();
       System.out.println(text);

        //click ok
        alert.accept();

        return text.contains(message);
    }

    public void submitRegistration() {

        click(By.cssSelector("[name='registration']"));
    }
    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
