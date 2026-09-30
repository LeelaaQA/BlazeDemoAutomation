package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ReservePage {

	private WebDriver driver;

	private By chooseFlightButtons = By.cssSelector("input[type='submit'][value='Choose This Flight']");

	public ReservePage(WebDriver driver) {
		this.driver = driver;
	}

	public void chooseFirstFlight() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List<WebElement> flights = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(chooseFlightButtons));

		if (flights.isEmpty()) {
			throw new RuntimeException("No available flights found.");
		}

		wait.until(ExpectedConditions.elementToBeClickable(flights.get(0))).click();

		wait.until(ExpectedConditions.urlContains("purchase.php"));
	}
}