package tests;

import manager.DataProviderUser;
import manager.ListenerTNG;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Listeners(ListenerTNG.class)
public class LoginTests extends TestBase {


    @BeforeMethod  //when we need to do check
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("I need logout");
        }
    }

    @Test(dataProvider = "loginDataUser", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) throws InterruptedException {
        logger.info("Tests start with user model-----"+user.toString());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test(dataProvider = "loginDataUserFromFile", dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelFromFile(User user) throws InterruptedException {
        logger.info("Tests start with user model-----"+user.toString());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }
    @DataProvider
    public Iterator<Object[]> loginData(){
        List <Object[]> list = new ArrayList<>();
        list.add(new Object[]{"teddy1@gmail.com","Teddy1206$"});
        list.add(new Object[]{"sonya@gmail.com","Ss12345$"});
        list.add(new Object[]{"noa@gmail.com","Nnoa12345$"});

        return list.iterator();
    }

    @Test(dataProvider = "loginData")
    public void loginSuccess(String email, String password){
        logger.info("Login with valid data: email: " +email + "& password: " +password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");
    }
    @Test(dataProvider = "loginDataClass", dataProviderClass = DataProviderUser.class)
    public void loginSuccess2(String email, String password){
        logger.info("Login with valid data: email: " +email + "& password: " +password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");
    }

    @Test (invocationCount = 2)
    public void loginSuccessNew() {
        logger.info("Login with valid data: email:'sonya@gmail.com' and password 'Teddy1206$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("sonya@gmail.com", "Ss12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");
    }


    @Test(groups = {"smoke"})
    public void loginWrongEmail() throws InterruptedException {
        logger.info("Test with data: email:'teddy1gmail.com' and password 'Teddy1206$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("teddy1gmail.com", "Teddy1206$");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");
    }

    @Test
    public void loginWrongPassword() throws InterruptedException {
        logger.info("Test with data: email:'teddy1@gmail.com' and password 'td23'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("teddy1@gmail.com", "td23");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");

    }

    @Test
    public void loginUnregisterUser() throws InterruptedException {
        logger.info("Test with data: email:'abcd@gmail.com' and password 'Teddy1206$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("abcd@gmail.com", "Teddy1206$");
        Thread.sleep(2000);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Tests success");
        logger.info("---------------------------------------------------------------------------------");

    }

}