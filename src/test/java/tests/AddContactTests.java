package tests;

import model.Contact;
import model.User;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isLogged())
        {
            //app.getHelperUser().login(new User().withEmail("teddy1@gmail.com").withPassword("Teddy1206$"));
            app.getHelperUser().login(User.builder().email("teddy1@gmail.com").password("Teddy1206$").build());
            // Lombok
        }
    }
    @Test
    public void addNewContactSuccessAllFields() throws InterruptedException {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        //Contact contact = Contact.builder()
        Contact contact = Contact.builder()  //lombok
                .name("Tom" + i)
                .lastName("Chan")
                .phone("192945" + i)
                .email("tom" + i + "@gmail.com")
                .address("USA")
                .description("friend")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        // Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact.getEmail()));

        //Assert.assertEquals(contact.getName(),app.getHelperContact().getCardName());
        //Assert.assertEquals(contact.getPhone(),app.getHelperContact().getCardPhone());
    }

    @Test
    public void addNewContactSuccessRequiredFields() {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        //Contact contact = Contact.builder()
        Contact contact = Contact.builder()  //lombok
                .name("Paula" + i)
                .lastName("Moro")
                .phone("192936" + i)
                .email("paula" + i + "@gmail.com")
                .address("USA")
                .build();
        // System.out.println(contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        // Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact.getEmail()));
    }

    @Test
    public void addNewContactNameNegative() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()  //lombok
                .name(null)
                .lastName("Moro")
                .phone("192936" + i)
                .email("paula" + i + "@gmail.com")
                .address("USA")
                .build();
        System.out.println(contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();
        Assert.assertTrue((app.getHelperContact().isButtonAddOnPage(contact)));
    }

    @Test
    public void addNewContactLastNameNegative() {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()  //lombok
                .name("Rick" + i)
                .lastName(null)
                .phone("192936" + i)
                .email("paula" + i + "@gmail.com")
                .address("USA")
                .build();
        System.out.println(contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();
        Assert.assertTrue((app.getHelperContact().isButtonAddOnPage(contact)));
    }

    @Test
    public void addNewContactPhoneNegative() throws InterruptedException {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()  //lombok
                .name("Rick" + i)
                .lastName("Frost")
                .phone("102938475600000"+i)
                .email("paula" + i + "@gmail.com")
                .address("USA")
                .build();
        System.out.println(contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();
        Assert.assertEquals(app.getHelperContact().isAlertFormIsDisplayed(), " Phone not valid: Phone number must contain only digits! And length min 10, max 15!");
    }
    @Test
    public void addNewContactEmailNegative() throws InterruptedException {
        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()  //lombok
                .name("Rick" + i)
                .lastName("Frost")
                .phone("102938"+i)
                .email("paula" + i + "gmail.com")
                .address("USA")
                .build();
        System.out.println(contact.toString());
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();
        Assert.assertEquals(app.getHelperContact().isAlertFormIsDisplayed(), "Email not valid: must be a well-formed email address");
    }
}
