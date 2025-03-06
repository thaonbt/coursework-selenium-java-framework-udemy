package UdemyRSA.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UdemyRSA.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		//initiallization
		super(driver);		// send driver to Parent class
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(css=".action__submit")
	private WebElement submit;
	
	@FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
	private WebElement selectCountry;
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		
		//Wait for dropdown display
		waitForElementToAppear(By.cssSelector(".ta-results"));
		
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	
}
