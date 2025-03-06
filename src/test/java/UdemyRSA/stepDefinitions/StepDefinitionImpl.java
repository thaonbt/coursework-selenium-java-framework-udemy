package UdemyRSA.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import UdemyRSA.PageObjects.CartPage;
import UdemyRSA.PageObjects.CheckoutPage;
import UdemyRSA.PageObjects.ConfirmationPage;
import UdemyRSA.PageObjects.LandingPage;
import UdemyRSA.PageObjects.ProductCatalogue;
import UdemyRSA.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
//	Given 	I landed on Ecommerce Page
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage= launchApplication();	
	}
	
//	Given Logged in with username <name> and password <password>
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
//	When 	I add product <productName> to Cart
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}
	
//	And 	Checkout <productName> and Submit the order
	@When("^Checkout (.+) and Submit the order$")
	public void Checkout_and_Submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match= cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
//	Then 	"THANKYOU FOR THE ORDER." message is displayed on Confirmation page
	@Then("^\"([^\"]*)\" message is displayed on Confirmation page$")
	public void message_is_displayed_on_Confirmation_page(String expectedString) {
		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase(expectedString));
		
		driver.close();
	}
	
//	Then "Incorrect email or password." message is displayed
	@Then("^\"([^\"]*)\" message is displayed$")
	public void message_is_displayed(String expectedString) {
		Assert.assertEquals(expectedString, landingPage.getErrorMessage());	//Correct
		
		driver.close();
	}
}
