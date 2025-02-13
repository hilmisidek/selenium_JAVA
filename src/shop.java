import java.text.DecimalFormat;
import java.time.Duration;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v130.page.model.WebAppManifest;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class shop {

    WebDriver driver;
    String test_product;
    By test_price;
    By btn_addCart;
    By added_cart_item;
    By added_cart_price;
    By cart_quantity;
    By cart_remove;
    By cart_total;
    DecimalFormat df;
    

    Double ex_price;

    public shop(WebDriver driver, String test_product,Double ex_price){
        this.driver=driver;
        this.test_product=test_product;
        this.ex_price=ex_price;
        this.test_price = By.xpath("//span[@class='shop-item-title'][text()='" + test_product + "']/following-sibling::div/span[@class='shop-item-price']");
        this.btn_addCart = By.xpath("//span[@class='shop-item-title'][text()='" + test_product + "']/following-sibling::div/button[text()='ADD TO CART']");
        this.added_cart_item = By.xpath("//div[@class='cart-items']//div[@class='cart-item cart-column']/span[text()='" + test_product +"']");
        this.added_cart_price = By.xpath("//div[@class='cart-items']//div[@class='cart-item cart-column']/span[text()='"+ test_product + "']/parent::div/following-sibling::span[@class='cart-price cart-column']");
        //this.cart_quantity = By.xpath("//div[@class='cart-items']//div[@class='cart-item cart-column']/span[text()='" + test_product + "']/parent::div/following-sibling::div[@class='cart-quantity cart-column']/input[@type='number']");
        this.cart_quantity = By.cssSelector("input.cart-quantity-input");
        this.cart_remove = By.xpath("//div[@class='cart-items']//div[@class='cart-item cart-column']/span[text()='" + test_product + "']/parent::div/following-sibling::div[@class='cart-quantity cart-column']/button[text()='REMOVE']");
        this.cart_total = By.cssSelector(".cart-total-price");
        this.df = new DecimalFormat("0.00");


    }

    public void check_price(){
    
        String priceString = driver.findElement(test_price).getText();
        String moneyString= priceString.substring(1,priceString.length());
        Double last_price = Double.parseDouble(moneyString);
        Assert.assertEquals(ex_price,last_price);
        
    }

    public void add_toCart(){
        driver.findElement(btn_addCart).click();
        Assert.assertTrue(driver.findElement(added_cart_item).isDisplayed());
        String add_cart_priceString = driver.findElement(added_cart_price).getText();
        Double add_cart_lastPrice = price_fetch(add_cart_priceString);
        Assert.assertEquals(ex_price, add_cart_lastPrice);

    }

    public void add_quantity(Integer quantity){
        String send_quantity= Integer.toString(quantity);
        //WebElement cart_quantity_element = driver.findElement(cart_quantity);
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(cart_quantity));
        String get_quantity= driver.findElement(cart_quantity).getDomAttribute("value");
        System.out.println("QUANTITY READ=" + get_quantity);
        driver.findElement(cart_quantity).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        driver.findElement(cart_quantity).sendKeys(send_quantity);
        driver.findElement(cart_total).click();

        Double price_check = price_fetch(driver.findElement(added_cart_price).getText());
        Double exp_cart_total = Double.parseDouble(df.format(quantity * price_check));
        
        Double cart_total_string = price_fetch(driver.findElement(cart_total).getText());
        Assert.assertEquals(exp_cart_total,cart_total_string);
        //Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        
        
       
    }

    public Double price_fetch(String price_raw){
        String price_text = price_raw.substring(1,price_raw.length());
        Double last_price = Double.parseDouble(price_text);
        return last_price;


    }

   
    
}
