package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
        wd.findElement(By.cssSelector("a[href='/login']")).click();
    }

    public void fillLoginRegistrationForm(String email, String password){
        //for email
        type(By.cssSelector("input[placeholder='Email']"), email);

        //for password
        type(By.cssSelector("input[placeholder='Password']"), password);

    }

    public void submitLogin(){
        click(By.cssSelector("button[name='login']"));
     }

    public void equals(){
       String text = wd.findElement(By.xpath("//*[text()='Login Failed with code 401']")).getText();

       Assert.assertEquals("Login Failed with code 401", text);


    }
}




