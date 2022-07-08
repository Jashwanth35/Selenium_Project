package ATS.SeleniumTrainings;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CS007_SpiceJet {
	public static WebDriver driver = null;
	static SoftAssert softAssert = new SoftAssert();

	@Test
	public void TicketBooking() throws IOException {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--silent");
			chromeOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
			driver.get("https://www.spicejet.com/");

			driver.findElement(By.xpath("(//input[@autocapitalize='sentences'])[1]")).sendKeys("Hyderabad");
			driver.findElement(By.xpath("(//input[@autocapitalize='sentences'])[2]")).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@autocapitalize='sentences'])[2]")).sendKeys("Go");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[normalize-space(text())='GOI']/parent::div")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"//div[@data-testid='undefined-month-June-2022']//div[@data-testid='undefined-calendar-day-27']"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[normalize-space(text())='Senior Citizen']/parent::div/parent::div/div"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[normalize-space(text())='Search Flight']/parent::div")).click();
			Thread.sleep(5000);

			WebElement DiscountTitle = driver.findElement(
					By.xpath("//div[contains(@class,'r-1wtj0ep r-tv6buo')]"));
			System.out.println("DiscountTitle " + DiscountTitle);
			String popuptitle = DiscountTitle.getText().trim();
			softAssert.assertTrue(DiscountTitle.isDisplayed(), "Senior Citizen Discount Bookings is not Displayed");
			softAssert.assertEquals(popuptitle, "Title is Senior Citizen Discount Bookings", "PopUP validation failed ");

			Thread.sleep(2000);
			driver.findElement(By.xpath(
					"(//div[contains(text(),'I have read and agreed to the')]/parent::div/parent::div//div/div/div)[1]"))
					.click();
			Thread.sleep(1000);

			driver.findElement(
					By.xpath("//*[normalize-space(text())='I have read and agreed to the']//following::div/div"))
					.click();
			
			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}