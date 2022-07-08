package ATS.SeleniumTrainings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CS003_Facebook {
	public static WebDriver driver = null;
	static SoftAssert softAssert = new SoftAssert();

	@Test
	public void FacebookLogin() {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
//			CASE STUDY 2: Do not print any driver informations in the log.
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--silent");
			driver = new ChromeDriver(chromeOptions);

			driver.manage().window().maximize();
			driver.get("https://www.facebook.com");

			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("G.jashwanth@gmail.com");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("wrongkeyword");
			driver.findElement(By.xpath("//button[@name='login']")).click();
			Thread.sleep(5000);
			String passworderror = driver.findElement(By.xpath("//div[@class='_9ay7']")).getText();

			System.out.println("Actual displayed password error is : " + passworderror);
			String ExpectedResult = "The password that you've entered is incorrect. Forgotten password?";
			softAssert.assertEquals(passworderror, ExpectedResult,
					"Facebook password validation error validation failed : ");

			Thread.sleep(1000);
			driver.close();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		softAssert.assertAll();

	}

}
