package UdemyRSA.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import UdemyRSA.PageObjects.CartPage;
import UdemyRSA.PageObjects.CheckoutPage;
import UdemyRSA.PageObjects.ConfirmationPage;
import UdemyRSA.PageObjects.OrdersPage;
import UdemyRSA.PageObjects.ProductCatalogue;
import UdemyRSA.TestComponents.BaseTest;

public class SubmitOrderTest_dataProviderObject extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(String email, String password, String productName) throws IOException {
	
//		String productName = "ZARA COAT 3";
//		ProductCatalogue productCatalogue = landingPage.loginApplication("tayoga2016@gmail.com", "TAYoga2016");
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match= cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	//To verify ZARA COAT 3 is displaying in Order page
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		//ZARA COAT 3
		ProductCatalogue productCatalogue = landingPage.loginApplication("tayoga2016@gmail.com", "TAYoga2016");
		OrdersPage ordersPage= productCatalogue.goToOrdersPage();
		
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() {
		//This is 2 dimension dataset	-> The tests will run twice for each dataset
		return new Object[][] {
				{"tayoga2016@gmail.com", "TAYoga2016", "ZARA COAT 3"}, 
				{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}
			};
	}
}
