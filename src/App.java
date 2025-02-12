import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.apache.poi;



public class App {
    
    
    static WebDriver driver = new ChromeDriver();

    shop newshop;

    @Before
    public void create_object(){
    newshop = new shop(driver,"Apple iPhone 12, 128GB, Black",905.99);
    }
        
    @Test
    public void test_url(){
        driver.get("https://qa-practice.netlify.app/auth_ecommerce");
      
    }

    @Test
    public void login(){
       
        loginPage dologin = new loginPage(driver);
        dologin.login("admin@admin.com", "admin123");
        dologin.check_header();



    }

    @Test
    public void check_price(){
        newshop.check_price();
        
    }

    @Test
    public void add_toCart(){
        newshop.add_toCart();
    }

    




    /*public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        //test_url();

        


    }*/
}
