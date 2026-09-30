package tests;

import pages.HomePage;
import pages.ReservePage;
import pages.PurchasePage;
import pages.ConfirmationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	protected WebDriver driver;
	protected HomePage homePage;
	protected ReservePage reservePage;
	protected PurchasePage purchasePage;
	protected ConfirmationPage confirmationPage;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://blazedemo.com/");

		homePage = new HomePage(driver);
		reservePage = new ReservePage(driver);
		purchasePage = new PurchasePage(driver);
		confirmationPage = new ConfirmationPage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}