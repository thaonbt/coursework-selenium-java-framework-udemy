package UdemyRSA.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UdemyRSA.AbstractComponents.AbstractComponent;

public class RegistrationPage extends AbstractComponent {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        //initialization
        super(driver); // send driver to Parent class
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement userEmail;

    @FindBy(id = "userMobile")
    private WebElement userMobile;

    @FindBy(css = "select[formcontrolname='occupation']")
    private WebElement occupation;

    @FindBy(css = "input[type='radio'][value='Male']")
    private WebElement genderMale;

    @FindBy(css = "input[type='radio'][value='Female']")
    private WebElement genderFemale;

    @FindBy(id = "userPassword")
    private WebElement userPassword;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(css = "input[type='checkbox'][formcontrolname='required']")
    private WebElement ageCheckbox;

    @FindBy(id = "login")
    private WebElement registerButton;

    public void enterFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void enterUserEmail(String email) {
        this.userEmail.sendKeys(email);
    }

    public void enterUserMobile(String mobile) {
        this.userMobile.sendKeys(mobile);
    }

    public void selectOccupation(String occupationValue) {
        this.occupation.sendKeys(occupationValue);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            this.genderMale.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            this.genderFemale.click();
        }
    }

    public void enterUserPassword(String password) {
        this.userPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        this.confirmPassword.sendKeys(password);
    }

    public void checkAgeCheckbox() {
        this.ageCheckbox.click();
    }

    public void clickRegisterButton() {
        this.registerButton.click();
    }

    public void completeRegistration(String firstName, String lastName, String email, String mobile, String occupation, String gender, String password, String confirmPassword) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterUserEmail(email);
        enterUserMobile(mobile);
        selectOccupation(occupation);
        selectGender(gender);
        enterUserPassword(password);
        enterConfirmPassword(confirmPassword);
        checkAgeCheckbox();
        clickRegisterButton();
    }
}