package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase {
    @BeforeMethod (alwaysRun = true) //when we need to do check
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test(groups = {"smoke","task"})
    
    public void registrationSuccess() throws InterruptedException {
        logger.info("Registration with valid data: email:'dom\" + i + \"@gmail.com'  and password 'Tima1206$'");
        Random random = new Random();
        int i = random.nextInt(100);
        String email = "dom" + i + "@gmail.com";

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, "Tima1206$");
        Thread.sleep(2000);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");
        //logout
    }

    @Test
    public void registrationWrongEmail() {
        logger.info("Registration with wrong email data: email:'domgmail.com'  and password 'Tima1206$'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("domgmail.com", "Tima1206$");
        app.getHelperUser().submitRegistration();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password format"));
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");
    }

    @Test
    public void registrationWrongPassword() {
        logger.info("Registration with wrong password data: email:'dom@gmail.com'  and password 'Tim1'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("dom@gmail.com", "Tim1");
        app.getHelperUser().submitRegistration();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");
    }

    @Test
    public void registrationUserAlreadyExists() {
        logger.info("Registration with used data: email:'teddy1@gmail.com'  and password 'Teddy1206$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("teddy1@gmail.com", "Teddy1206$");
        app.getHelperUser().submitRegistration();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("User already exist"));
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");
    }
}
