package UdemyRSA.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import UdemyRSA.PageObjects.CartPage;
import UdemyRSA.PageObjects.ProductCatalogue;
import UdemyRSA.TestComponents.BaseTest;
import UdemyRSA.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

//	public static void main(String[] args) {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {
	
		landingPage.loginApplication("tayoga2016@gmail.com", "2016");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());	//Correct
//		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());	//Incorrect
	}
	
	@Test
	public void ProductErrorValidation() throws IOException {
	
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("tayoga2016@gmail.com", "TAYoga2016");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match= cartPage.verifyProductDisplay("ZARA COAT 33333");
		Assert.assertFalse(match);
	}

}
