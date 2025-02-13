import static org.junit.Assert.assertEquals;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.apache.poi;


@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class App {
    
    
    static WebDriver driver = new ChromeDriver();

    shop newshop;

    @Before
    public void create_object(){
    newshop = new shop(driver,"Apple iPhone 12, 128GB, Black",905.99);
    }
        
    @Test
    public void test_1_url(){
        driver.get("https://qa-practice.netlify.app/auth_ecommerce");
      
    }

    @Test
    public void test_2_login(){
       
        loginPage dologin = new loginPage(driver);
        dologin.login("admin@admin.com", "admin123");
        dologin.check_header();



    }

    @Test
    public void test_3_check_price(){
        newshop.check_price();
        
    }

    @Test
    public void test_4_add_toCart(){
        newshop.add_toCart();
    }

    @Test
    public void test_5_add_tocart_quantity(){
        newshop.add_quantity(3);
    }

   


    /*public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        //test_url();

        


    }*/
}
