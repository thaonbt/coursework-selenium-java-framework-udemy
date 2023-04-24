package UdemyRSA.Tests;

import java.io.IOException;
import java.util.HashMap;
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

public class SubmitOrderTest_dataProviderHashMap extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
	
//		String productName = "ZARA COAT 3";
//		ProductCatalogue productCatalogue = landingPage.loginApplication("tayoga2016@gmail.com", "TAYoga2016");
//		ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match= cartPage.verifyProductDisplay(input.get("productName"));
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
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "tayoga2016@gmail.com");
		map1.put("password", "TAYoga2016");
		map1.put("productName", "ZARA COAT 3");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "shetty@gmail.com");
		map2.put("password", "Iamking@000");
		map2.put("productName", "ADIDAS ORIGINAL");
	
		return new Object[][] {
				{map1}, 
				{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}
			};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		//This is 2 dimension dataset	-> The tests will run twice for each dataset
//		return new Object[][] {
//				{"tayoga2016@gmail.com", "TAYoga2016", "ZARA COAT 3"}, 
//				{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}
//			};
//	}
}
