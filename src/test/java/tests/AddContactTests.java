package tests;

import model.Contact;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

public class AddContactTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged())
        {
            app.getHelperUser().login(User.builder().email("teddy1@gmail.com").password("Teddy1206$").build());
            // Lombok
        }
    }
    @Test
    public void addNewContactSuccessAllFields()  {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()  //lombok
                .name("Tom" + i)
                .lastName("Chan")
                .phone("192945" + i)
                .email("tom" + i + "@gmail.com")
                .address("USA")
                .description("friend")
                .build();
        System.out.println(contact.toString());
        logger.info("Tests start with data : " +contact.toString());

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();


        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact.getEmail()));

    }

    @Test(groups = {"smoke"})
    public void addNewContactSuccessRequiredFields() {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()  //lombok
                .name("Paula" + i)
                .lastName("Moro")
                .phone("192936" + i)
                .email("paula" + i + "@gmail.com")
                .address("USA")
                .build();
         System.out.println(contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact.getEmail()));
    }

    @Test
    public void addNewContactWrongName() {
        Contact contact = Contact.builder()  //lombok
                .name("")
                .lastName("Moro")
                .phone("1111111333")
                .email("luck@gmail.com")
                .address("USA")
                .description("wrong name")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();
        Assert.assertTrue(app.getHelperContact().issAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongLastName() {

        Contact contact = Contact.builder()  //lombok
                .name("Rick")
                .lastName("")
                .phone("1929361234567")
                .email("rick@gmail.com")
                .address("USA")
                .description("wrong last name")
                .build();
        System.out.println(contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().issAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongPhone()  {

        Contact contact = Contact.builder()  //lombok
                .name("Rick")
                .lastName("Frost")
                .phone("1029")
                .email("paula@gmail.com")
                .address("USA")
                .description("wrong phone")
                .build();
        System.out.println(contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().issAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
    }
    @Test
    public void addNewContactWrongEmail() {
        Contact contact = Contact.builder()  //lombok
                .name("Rick" )
                .lastName("Frost")
                .phone("1029388765432")
                .email("paulagmail.com")
                .address("USA")
                .description("wrong email")
                .build();
        System.out.println(contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().issAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Email not valid: must be a well-formed email address"));
    }
}
