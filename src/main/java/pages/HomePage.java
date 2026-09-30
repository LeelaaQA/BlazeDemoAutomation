package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

	private WebDriver driver;

	// Locators
	private By departureCity = By.name("fromPort");
	private By destinationCity = By.name("toPort");
	private By findFlightsButton = By.cssSelector("input[type='submit']");

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Select departure city
	public void selectDepartureCity(String city) {
		Select select = new Select(driver.findElement(departureCity));
		select.selectByVisibleText(city);
	}

	// Select destination city
	public void selectDestinationCity(String city) {
		Select select = new Select(driver.findElement(destinationCity));
		select.selectByVisibleText(city);
	}

	// Click Find Flights
	public void clickFindFlights() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.elementToBeClickable(findFlightsButton)).click();

		wait.until(ExpectedConditions.urlContains("reserve.php"));
	}

	// Complete flight search
	public void searchFlights(String fromCity, String toCity) {
		selectDepartureCity(fromCity);
		selectDestinationCity(toCity);
		clickFindFlights();
	}
}