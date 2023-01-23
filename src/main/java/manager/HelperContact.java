package manager;

import model.Contact;
import org.openqa.selenium.Alert;
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

    public String getCardName(){

        return wd.findElement(By.cssSelector("h2")).getText();
    }
    public String getCardPhone(){

        return wd.findElement(By.cssSelector("h3")).getText();
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
    public boolean isContactAddedByPhone(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for(WebElement el : list){
            if(el.getText().equals(name)){
                return true;
            }
        }
        return false;
    }
   public boolean isContactAddedByEmail(String email)  {
       click(By.xpath("//h2"));
       click(By.xpath("//button[text()='Edit']"));
       List<WebElement> list = wd.findElements(By.xpath("//input[@placeholder='email']"));
       for(WebElement el : list){
           System.out.println(el.toString());
           if(el.getText().equals(email)){
               return true;
           }
       }
       click(By.xpath("//button[text()='Save']"));
       return false;
   }
    public boolean isButtonAddOnPage(Contact contact) {
          return wd.findElement(By.cssSelector("a[href='/add']")).isDisplayed();
          // return wd.findElement(By.cssSelector("a[href='/add']")).isEnabled();
        }

    public String isAlertFormIsDisplayed() throws InterruptedException {
        click(By.xpath("//b[text()='Save']"));
        Thread.sleep(2000);
        Alert alert = wd.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        alert.accept();
        return text;

    }

    public void refresh() {
        wd.navigate().refresh();
    }
}



