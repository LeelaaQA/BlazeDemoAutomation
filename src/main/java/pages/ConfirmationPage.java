package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage {

	private WebDriver driver;

	private By confirmationMessage = By.xpath("//h1[contains(text(),'Thank you for your purchase today!')]");

	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isConfirmationDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).isDisplayed();
	}

	public String getConfirmationMessage() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).getText();
	}
}