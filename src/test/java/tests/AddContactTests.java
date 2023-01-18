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

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("teddy1@gmail.com").withPassword("Teddy1206$"));
        }
    }
    @Test
    public void addNewContactSuccess() {
        Contact contact = Contact.builder()
                .name("Tom")
                .lastName("Chan")
                .phone("19294567839")
                .email("tom@gmail.com")
                .address("USA")
                .description("friend")
                .build();

        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();

        Assert.assertEquals(contact.getName(),app.getHelperContact().getCardName());
        Assert.assertEquals(contact.getPhone(),app.getHelperContact().getCardPhone());
    }
}
