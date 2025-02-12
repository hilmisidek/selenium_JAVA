import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class loginPage {
   protected WebDriver driver;

   By txt_username = By.xpath("//input[@type='email'][@name='emailAddress']");
   By txt_password = By.xpath("//input[@type='password'][@name='password']");
   By btn_submit = By.xpath("//button[@id='submitLoginBtn']");
   By lbl_cart=By.xpath("//h2[text()='SHOPPING CART']");

   public loginPage (WebDriver driver){
   this.driver = driver;
   }

   public void login(String username, String password){

        driver.findElement(txt_username).sendKeys(username);
        driver.findElement(txt_password).sendKeys(password);
        driver.findElement(btn_submit).click();
   }

   public void check_header(){
        String curheader = driver.findElement(lbl_cart).getText();
        Assert.assertEquals("SHOPPING CART", curheader);

   }


}
