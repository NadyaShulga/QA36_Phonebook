package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends TestBase {

    @Test
    public void loginSuccess() throws InterruptedException {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("teddy1@gmail.com","Teddy1206$");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();

        //*[text()='Sign Out']

    }
    @Test
    public void loginWrongEmail() throws InterruptedException {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("teddy1gmail.com","Teddy1206$");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        app.getHelperUser().equals();
    }
    @Test
    public void loginWrongPassword(){

    }
    @Test
    public void loginUnregisterUser(){

    }
}
