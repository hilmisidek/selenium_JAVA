import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class shop {

    WebDriver driver;
    String test_product;
    By test_price;
    By btn_addCart;
    By added_cart_item;
    By added_cart_price;

    Double ex_price;

    public shop(WebDriver driver, String test_product,Double ex_price){
        this.driver=driver;
        this.test_product=test_product;
        this.ex_price=ex_price;
        this.test_price = By.xpath("//span[@class='shop-item-title'][text()='" + test_product + "']/following-sibling::div/span[@class='shop-item-price']");
        this.btn_addCart = By.xpath("//span[@class='shop-item-title'][text()='" + test_product + "']/following-sibling::div/button[text()='ADD TO CART']");
        this.added_cart_item = By.xpath("//div[@class='cart-items']//div[@class='cart-item cart-column']/span[text()='" + test_product +"']");
        this.added_cart_price = By.xpath("//div[@class='cart-items']//div[@class='cart-item cart-column']/span[text()='"+ test_product + "']/parent::div/following-sibling::span[@class='cart-price cart-column']");
        

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
        String add_cart_moneyString = add_cart_priceString.substring(1,add_cart_priceString.length());
        Double add_cart_lastPrice = Double.parseDouble(add_cart_moneyString);
        Assert.assertEquals(ex_price, add_cart_lastPrice);

    }

    public void add_quantity(){

        
    }

    
}
