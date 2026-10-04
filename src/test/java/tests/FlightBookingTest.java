package tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FlightBookingTest extends BaseTest {

	@DataProvider(name = "flightData")
	public Object[][] flightData() {
		return new Object[][] { { "Boston", "New York" }, { "Paris", "London" }, { "Portland", "Berlin" } };
	}

	@Test(groups = { "smoke" })
	public void TC01_verifyHomepageLoadsAndDropdownsVisible() {

		Assert.assertTrue(driver.getTitle().contains("BlazeDemo"), "BlazeDemo homepage did not load");

		Assert.assertTrue(driver.findElement(By.name("fromPort")).isDisplayed(),
				"Departure city dropdown is not visible");

		Assert.assertTrue(driver.findElement(By.name("toPort")).isDisplayed(),
				"Destination city dropdown is not visible");
	}

	@Test(dataProvider = "flightData", groups = { "functional" })
	public void TC04_multipleBookingsWithDifferentDataSets(String fromCity, String toCity) {

		homePage.searchFlights(fromCity, toCity);

		reservePage.chooseFirstFlight();

		purchasePage.completePurchase("Leelaa Vinothinie", "123 Test Street", "Cuddalore", "Tamil Nadu", "607001",
				"Visa", "1234567890123456", "12", "2030", "Leelaa Vinothinie");

		Assert.assertTrue(confirmationPage.isConfirmationDisplayed(), "Confirmation message was not displayed");

		Assert.assertEquals(confirmationPage.getConfirmationMessage(), "Thank you for your purchase today!");
	}

	@Test(groups = { "smoke", "functional" })
	public void TC02_searchFlightsWithValidCities() {

		homePage.searchFlights("Boston", "London");

		Assert.assertTrue(driver.getCurrentUrl().contains("reserve.php"), "Reserve page was not displayed");

		reservePage.chooseFirstFlight();

		Assert.assertTrue(driver.getCurrentUrl().contains("purchase.php"), "Purchase page was not displayed");
	}

	@Test(groups = { "smoke", "functional" })
	public void TC03_completeFlightBooking() {

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
	public void TC05_blankCreditCard() {

		homePage.searchFlights("Boston", "New York");
		reservePage.chooseFirstFlight();

		Assert.assertTrue(driver.getCurrentUrl().contains("purchase.php"), "Purchase page was not displayed");

		purchasePage.enterPassengerDetails("Leelaa Vinothinie", "123 Test Street", "Cuddalore", "Tamil Nadu", "607001");

		purchasePage.selectCardType("Visa");

		// Intentionally leave credit card number blank
		purchasePage.enterCardDetails("", "12", "2030", "Leelaa Vinothinie");

		purchasePage.clickPurchaseFlight();

		// Expected: booking should NOT be confirmed
		Assert.assertFalse(driver.getCurrentUrl().contains("confirmation.php"),
				"Defect: Application allowed booking without a credit card number");
	}

	@Test(groups = { "negative" })
	public void TC06_invalidCreditCardCharacters() {

		homePage.searchFlights("Boston", "New York");
		reservePage.chooseFirstFlight();

		Assert.assertTrue(driver.getCurrentUrl().contains("purchase.php"), "Purchase page was not displayed");

		purchasePage.enterPassengerDetails("Leelaa Vinothinie", "123 Test Street", "Cuddalore", "Tamil Nadu", "607001");

		purchasePage.selectCardType("Visa");

		// Intentionally enter invalid non-numeric card value
		purchasePage.enterCardDetails("ABCDEF123456", "12", "2030", "Leelaa Vinothinie");

		purchasePage.clickPurchaseFlight();

		// Expected: application should not confirm an invalid card
		Assert.assertFalse(driver.getCurrentUrl().contains("confirmation.php"),
				"Defect: Application allowed booking with non-numeric credit card");
	}

	@Test(groups = { "negative" })
	public void requiredFieldsBlankTest() {

		homePage.searchFlights("Paris", "London");
		reservePage.chooseFirstFlight();

		Assert.assertTrue(driver.getCurrentUrl().contains("purchase.php"), "Purchase page was not displayed");

		// Leave all passenger and payment fields blank
		purchasePage.clickPurchaseFlight();

		// Expected: booking should NOT be confirmed
		Assert.assertFalse(driver.getCurrentUrl().contains("confirmation.php"),
				"Defect: Application allowed booking with required fields left blank");
	}

	@Test(groups = { "negative" })
	public void TC07_sameDepartureAndDestinationCity() {

		Select departureSelect = new Select(driver.findElement(By.name("fromPort")));
		Select destinationSelect = new Select(driver.findElement(By.name("toPort")));

		boolean commonCityFound = false;

		for (WebElement departureOption : departureSelect.getOptions()) {

			String departureCity = departureOption.getText().trim();

			for (WebElement destinationOption : destinationSelect.getOptions()) {

				String destinationCity = destinationOption.getText().trim();

				if (departureCity.equals(destinationCity)) {
					commonCityFound = true;
					break;
				}
			}

			if (commonCityFound) {
				break;
			}
		}

		// Expected: No common city should be available for
		// departure and destination in the current BlazeDemo UI.
		Assert.assertFalse(commonCityFound, "A common city is available in both departure and destination lists.");
	}

}