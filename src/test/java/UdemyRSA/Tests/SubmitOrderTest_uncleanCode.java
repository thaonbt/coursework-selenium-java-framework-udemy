package UdemyRSA.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import UdemyRSA.PageObjects.CartPage;
import UdemyRSA.PageObjects.CheckoutPage;
import UdemyRSA.PageObjects.ConfirmationPage;
import UdemyRSA.PageObjects.ProductCatalogue;
import UdemyRSA.TestComponents.BaseTest;

public class SubmitOrderTest_uncleanCode extends BaseTest {

//	public static void main(String[] args) {

	@Test
	public void submitOrder() throws IOException {
	
		String productName = "ZARA COAT 3";
		
//		LandingPage landingPage = launchApplication();
		
//		WebDriver driver = new ChromeDriver();
////		driver.manage().window().maximize();
//		//Implicit Wait
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
////		//Explicit Wait
////		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		
//		LandingPage landingPage = new LandingPage(driver);
//		landingPage.goTo();
//		landingPage.loginApplication("tayoga2016@gmail.com", "TAYoga2016");
		ProductCatalogue productCatalogue = landingPage.loginApplication("tayoga2016@gmail.com", "TAYoga2016");
//		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
//		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
//		productCatalogue.goToCartPage();
		CartPage cartPage = productCatalogue.goToCartPage();
//		CartPage cartPage = new CartPage(driver);
		Boolean match= cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
//		checkoutPage.submitOrder();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
//		/**Navigate to page*/
//		driver.get("https://rahulshettyacademy.com/client");
//		
//		/**Do Login*/
//		driver.findElement(By.id("userEmail")).sendKeys("tayoga2016@gmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("TAYoga2016");
//		driver.findElement(By.id("login")).click();
//		
//		/**Do Add To Cart*/
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//		
//		for(WebElement product :products) {
//			if(product.findElement(By.cssSelector("b")).getText()=="ZARA COAT 3") {
//				product.findElement(By.cssSelector(".w-10")).click();
//			}
//		}
//		
//	WebElement prod = products.stream().filter(
//				product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//		
//		//ng-animating
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
//		//toast-container
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//
//		
//		//Go to Cart
//		driver.findElement(By.cssSelector("[routerlink *= 'cart']")).click();
//		
//		//Check added products
//		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
////		cartProducts.stream().filter(
////				cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
//	Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
//	Assert.assertTrue(match);
//	
//		//Checkout
//		driver.findElement(By.cssSelector(".totalRow button")).click();
//		
//		//Input Shipping Information
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
//		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//		
////		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)"));
//		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
//		
//		//Place Order
//		driver.findElement(By.cssSelector(".action__submit")).click();
//		
//		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
//		driver.close();
		
	}

}
