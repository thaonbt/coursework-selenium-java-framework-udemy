
@tag
Feature: Purchase the order from Ecommerce website
  Description for the test

	Background:
	Given 	I landed on Ecommerce Page

  @tag2
  Scenario Outline: submitOrder
    Positive Test of Submitting the order
    
    Given Logged in with username <name> and password <password>
    When 	I add product <productName> to Cart
    And 	Checkout <productName> and Submit the order
    Then 	"THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples: 
      | name  								| password 		| productName |
      | rahulshetty@gmail.com | IamKing@000 | ZARA COAT 3 |
