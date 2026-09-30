package pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PurchasePage {

	private WebDriver driver;

	private By name = By.name("inputName");
	private By address = By.name("address");
	private By city = By.name("city");
	private By state = By.name("state");
	private By zipCode = By.name("zipCode");

	private By cardType = By.name("cardType");
	private By creditCardNumber = By.name("creditCardNumber");
	private By creditCardMonth = By.name("creditCardMonth");
	private By creditCardYear = By.name("creditCardYear");
	private By nameOnCard = By.name("nameOnCard");

	private By purchaseFlightButton = By.cssSelector("input[type='submit']");

	public PurchasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterPassengerDetails(String passengerName, String passengerAddress, String passengerCity,
			String passengerState, String passengerZipCode) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(name)).sendKeys(passengerName);

		driver.findElement(address).sendKeys(passengerAddress);
		driver.findElement(city).sendKeys(passengerCity);
		driver.findElement(state).sendKeys(passengerState);
		driver.findElement(zipCode).sendKeys(passengerZipCode);
	}

	public void selectCardType(String type) {
		Select select = new Select(driver.findElement(cardType));
		select.selectByVisibleText(type);
	}

	public void enterCardDetails(String cardNumber, String month, String year, String cardHolderName) {

		driver.findElement(creditCardNumber).sendKeys(cardNumber);
		driver.findElement(creditCardMonth).sendKeys(month);
		driver.findElement(creditCardYear).sendKeys(year);
		driver.findElement(nameOnCard).sendKeys(cardHolderName);
	}

	public void clickPurchaseFlight() {
		driver.findElement(purchaseFlightButton).click();
	}

	public void completePurchase(String passengerName, String passengerAddress, String passengerCity,
			String passengerState, String passengerZipCode, String cardTypeValue, String cardNumber, String month,
			String year, String cardHolderName) {

		enterPassengerDetails(passengerName, passengerAddress, passengerCity, passengerState, passengerZipCode);

		selectCardType(cardTypeValue);

		enterCardDetails(cardNumber, month, year, cardHolderName);

		clickPurchaseFlight();
	}
}