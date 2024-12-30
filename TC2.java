package resources;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;



@SuppressWarnings("unused")
public class TC2 {
	
	public static WebDriverWait wait;
	public static WebDriver driver;

   public static void main(String[] args) {
	
	   WebDriverManager.edgedriver().setup();
       driver = new EdgeDriver(); // Initialize the ChromeDriver
       driver.manage().window().maximize(); // Maximize the window
       
       // Open eBay website
       driver.get("https://www.ebay.com");

       // Step 2: Search for "book"
       WebElement searchBox = driver.findElement(By.id("gh-ac"));
       searchBox.sendKeys("book");
       WebElement searchButton = driver.findElement(By.id("gh-btn"));
       searchButton.click();
       
       wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-item")));
       

       

       // Step 3: Click on the first book in the list
       WebElement firstBook = driver.findElement(By.cssSelector(".s-item:nth-child(1) .s-item__link"));
       firstBook.click();
       
       wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("atcRedesignId_btn")));
       
      
       // Step 4: Click on the 'Add to cart' button
       WebElement addToCartButton = driver.findElement(By.id("atcRedesignId_btn"));
       addToCartButton.click();
       
       wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("gh-cart-n")));

      

       WebElement cartCountElement = driver.findElement(By.id("gh-cart-n"));
       String cartCountText = cartCountElement.getText();

       // Step 6: Verify the cart has the expected number of items
       assertNotNull(cartCountText, "Cart count is null");
       assertTrue(cartCountText.matches("\\d+"), "Cart count is not a number");
       int cartCount = Integer.parseInt(cartCountText);
       assertTrue(cartCount > 0, "Cart should have at least 1 item");

       // Optionally, print the number of items in the cart
       System.out.println("Cart count: " + cartCount);

       // Step 7: Close the browser
       driver.quit();
}
    
    
}

