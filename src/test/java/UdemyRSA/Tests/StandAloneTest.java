package UdemyRSA.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		//Navigate to page
		driver.get("https://rahulshettyacademy.com/client");
		
		//Do Login
		driver.findElement(By.id("userEmail")).sendKeys("tayoga2016@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("TAYoga2016");
		driver.findElement(By.id("login")).click();
		
		//Do Add To Cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
//		for(WebElement product :products) {
//			if(product.findElement(By.cssSelector("b")).getText()=="ZARA COAT 3") {
//				product.findElement(By.cssSelector(".w-10")).click();
//			}
//		}
		
	WebElement prod = products.stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//toast-container
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		
		//Go to Cart
		driver.findElement(By.cssSelector("[routerlink *= 'cart']")).click();
		
		//Check added products
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//		cartProducts.stream().filter(
//				cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	
		//Checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Input Shipping Information
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
//		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"));
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		
		//Place Order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		
	}

}
