package manager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;


public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
        wd.findElement(By.cssSelector("a[href='/login']")).click();
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
       Alert alert = wd.switchTo().alert();
       String text = alert.getText();
       System.out.println(text);

        //click ok
        alert.accept();
        //click cancel   ---->  //alert.dismiss();
        //alert.sendKeys("Hello");

        return text.contains(message);
    }

    public void submitRegistration() {
        click(By.cssSelector("[name='registration']"));
    }
}
