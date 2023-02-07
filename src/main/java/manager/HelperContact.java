package manager;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {

        super(wd);
    }
    public void openAddContactForm() {

        click(By.cssSelector("a[href='/add']"));
    }
    public void fillAddContactForm(Contact contact) {
        type(By.cssSelector("input[placeholder='Name']"), contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());
    }
    public void submitAddContactForm() {

        click(By.xpath("//b[text()='Save']"));
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for(WebElement el : list){
            if(el.getText().equals(name)){

                return true;
            }
        }
        return false;
    }
    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for(WebElement el : list){
            if(el.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }
   public boolean isContactAddedByEmail(String email)  {
       List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
       for(WebElement el : list){
           el.click();
           String text = wd.findElement((By.cssSelector(".contact-item-detailed_card__50dTS"))).getText();
           if(text.contains(email)){
               return true;
           }
       }
       return false;
   }

    public boolean issAddPageStillDisplayed() {
        return wd.findElements(By.cssSelector("a.active[href='/add']")).size()>0;
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}



