package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightBookingTest extends BaseTest {

	@DataProvider(name = "flightData")
	public Object[][] flightData() {
		return new Object[][] { { "Boston", "New York" }, { "Paris", "London" }, { "Portland", "Berlin" } };
	}

	@Test(dataProvider = "flightData", groups = { "functional" })
	public void multipleFlightBookingTest(String fromCity, String toCity) {

		homePage.searchFlights(fromCity, toCity);

		reservePage.chooseFirstFlight();

		purchasePage.completePurchase("Leelaa Vinothinie", "123 Test Street", "Cuddalore", "Tamil Nadu", "607001",
				"Visa", "1234567890123456", "12", "2030", "Leelaa Vinothinie");

		Assert.assertTrue(confirmationPage.isConfirmationDisplayed(), "Confirmation message was not displayed");

		Assert.assertEquals(confirmationPage.getConfirmationMessage(), "Thank you for your purchase today!");
	}

	@Test(groups = { "smoke", "functional" })
	public void searchAndSelectFlightTest() {

		homePage.searchFlights("Boston", "London");

		Assert.assertTrue(driver.getCurrentUrl().contains("reserve.php"), "Reserve page was not displayed");

		reservePage.chooseFirstFlight();

		Assert.assertTrue(driver.getCurrentUrl().contains("purchase.php"), "Purchase page was not displayed");
	}

	@Test(groups = { "smoke", "functional" })
	public void completeFlightBookingTest() {

		// Step 1: Search for a flight
		homePage.searchFlights("Boston", "New York");

		// Step 2: Select the first available flight
		reservePage.chooseFirstFlight();

		// Step 3: Complete passenger and payment details
		purchasePage.completePurchase("Leelaa Vinothinie", "123 Test Street", "Cuddalore", "Tamil Nadu", "607001",
				"Visa", "1234567890123456", "12", "2030", "Leelaa Vinothinie");

		// Step 4: Verify confirmation page
		Assert.assertTrue(confirmationPage.isConfirmationDisplayed(), "Confirmation message was not displayed");

		Assert.assertEquals(confirmationPage.getConfirmationMessage(), "Thank you for your purchase today!");
	}

	@Test(dataProvider = "flightData", groups = { "functional" })
	public void multipleFlightSearchTest(String fromCity, String toCity) {

		homePage.searchFlights(fromCity, toCity);

		Assert.assertTrue(driver.getCurrentUrl().contains("reserve.php"), "Reserve page was not displayed");
	}

	@Test(groups = { "negative" })
	public void blankCreditCardBehaviorTest() {

		homePage.searchFlights("Boston", "New York");

		reservePage.chooseFirstFlight();

		Assert.assertTrue(driver.getCurrentUrl().contains("purchase.php"), "Purchase page was not displayed");

		purchasePage.enterPassengerDetails("Leelaa Vinothinie", "123 Test Street", "Cuddalore", "Tamil Nadu", "607001");

		purchasePage.selectCardType("Visa");

		purchasePage.enterCardDetails("", "12", "2030", "Leelaa Vinothinie");

		purchasePage.clickPurchaseFlight();

		Assert.assertTrue(driver.getCurrentUrl().contains("confirmation.php"),
				"Application did not reach confirmation page");
	}

	@Test(groups = { "negative" })
	public void nonNumericCreditCardTest() {

		homePage.searchFlights("Boston", "New York");

		reservePage.chooseFirstFlight();

		Assert.assertTrue(driver.getCurrentUrl().contains("purchase.php"), "Purchase page was not displayed");

		purchasePage.enterPassengerDetails("Leelaa Vinothinie", "123 Test Street", "Cuddalore", "Tamil Nadu", "607001");

		purchasePage.selectCardType("Visa");

		// Intentionally enter non-numeric credit card value
		purchasePage.enterCardDetails("ABCDEF123456", "12", "2030", "Leelaa Vinothinie");

		purchasePage.clickPurchaseFlight();

		System.out.println("URL after non-numeric card: " + driver.getCurrentUrl());
	}

	@Test(groups = { "negative" })
	public void requiredFieldsBlankTest() {

		homePage.searchFlights("Paris", "London");

		reservePage.chooseFirstFlight();

		Assert.assertTrue(driver.getCurrentUrl().contains("purchase.php"), "Purchase page was not displayed");

		// Leave all passenger/payment fields blank
		purchasePage.clickPurchaseFlight();

		System.out.println("URL after blank required fields: " + driver.getCurrentUrl());
	}

	@Test(groups = { "negative" })
	public void sameDepartureDestinationTest() {

		Select departureSelect = new Select(driver.findElement(By.name("fromPort")));

		Select destinationSelect = new Select(driver.findElement(By.name("toPort")));

		boolean commonCityFound = false;

		for (WebElement departureOption : departureSelect.getOptions()) {

			String departureCity = departureOption.getText();

			for (WebElement destinationOption : destinationSelect.getOptions()) {

				String destinationCity = destinationOption.getText();

				if (departureCity.equals(destinationCity)) {
					commonCityFound = true;
					break;
				}
			}

			if (commonCityFound) {
				break;
			}
		}

		Assert.assertFalse(commonCityFound, "A city is available in both departure and destination lists.");
	}

}