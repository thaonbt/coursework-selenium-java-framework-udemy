package UdemyRSA.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UdemyRSA.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css="tr td:nth-of-type(2)")
	private List<WebElement> ordersProductNames;
	
	public OrdersPage(WebDriver driver) {
		//initiallization
		super(driver);		// send driver to Parent class
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = ordersProductNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
