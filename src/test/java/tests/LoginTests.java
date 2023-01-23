package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends TestBase {
    @BeforeMethod  //when we need to do check
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }
    @Test
    public void loginSuccessModel() throws InterruptedException {
       User user = User.builder().build();
        // User user = new User().withEmail("teddy1@gmail.com").withPassword("Teddy1206$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        //logout
    }
    @Test
    public void loginSuccess() throws InterruptedException {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("teddy1@gmail.com", "Teddy1206$");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        //logout
    }

    @Test
    public void loginSuccessNew() throws InterruptedException {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("sonya@gmail.com", "Ss12345$");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        //logout
    }

    @Test
    public void loginWrongEmail() throws InterruptedException {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("teddy1gmail.com", "Teddy1206$");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
    }

    @Test
    public void loginWrongPassword() throws InterruptedException {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("teddy1@gmail.com", "td23");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));

    }

    @Test
    public void loginUnregisterUser() throws InterruptedException {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("abcd@gmail.com", "Teddy1206$");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));

    }
}