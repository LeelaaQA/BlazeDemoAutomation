package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

	private WebDriver driver;

	private By confirmationMessage = By.xpath("//h1[contains(text(),'Thank you for your purchase today!')]");

	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isConfirmationDisplayed() {
		return driver.findElement(confirmationMessage).isDisplayed();
	}

	public String getConfirmationMessage() {
		return driver.findElement(confirmationMessage).getText();
	}
}