package ATS.SeleniumTrainings;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import io.github.bonigarcia.wdm.WebDriverManager;

public class CS004_LinkedIn {
	public static WebDriver driver = null;
	static SoftAssert softAssert = new SoftAssert();
	public static Logger log = Logger.getLogger(CS004_LinkedIn.class.getName());

	@Test
	public void LinkedinLogin() {

		try {

			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
			// CASE STUDY2: Do not print any driver informations in the log.
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--silent");
			driver = new ChromeDriver(chromeOptions);

			driver.manage().window().maximize();
			driver.get("https://www.Linkedin.com");
			driver.findElement(By.xpath("//a[@class='sign-in-form__forgot-password-link']")).click();
			String ResetPaswordTitle = driver.getTitle();
			// System.out.println("Actual Title "+ ResetPaswordTitle);
			softAssert.assertEquals(ResetPaswordTitle, "Reset Password | LinkedIn",
					"LinkedIn ResetPassword validation failed : ");
			// log.info("ResetPaswordTitle " + ResetPaswordTitle);

			Thread.sleep(1000);

			driver.findElement(By.xpath("//a[contains(@class,'signin')]")).click();
			String LoginTitle = driver.getTitle();
			// System.out.println("Actual Title " + LoginTitle);
			softAssert.assertEquals(LoginTitle, "LinkedIn Login, Sign in | LinkedIn",
					"LinkedIn Login Title validation failed : ");

			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("G.jashwanth@gmail.com");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Vasudha@123");
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			Thread.sleep(1000);

			String Title = driver.getTitle();
			// System.out.println("Actual Title " +Title);
			softAssert.assertEquals(Title, "(99+) Feed | LinkedIn", "Failed to login to LinkedIn : ");

			softAssert.assertAll();

			
			Thread.sleep(1000);
			driver.close();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

//	@Test(priority = 0)
//	public void LaunchURL() {
//
//		try {
//
//			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
//			System.setProperty("webdriver.chrome.silentOutput", "true");
//			WebDriverManager.chromedriver().setup();
//			ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.addArguments("--silent");
//			driver = new ChromeDriver(chromeOptions);
//			driver.manage().window().maximize();
//			driver.get("https://www.Linkedin.com");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	@Test(priority = 1)
//	public void Linkedin_PasswordReset() {
//
//		try {
//
//			driver.findElement(By.xpath("//a[@class='sign-in-form__forgot-password-link']")).click();
//			String ResetPaswordTitle = driver.getTitle();
//			softAssert.assertEquals(ResetPaswordTitle, "Reset Password | LinkedIn",
//					"LinkedIn ResetPassword validation failed : ");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		softAssert.assertAll();
//
//	}
//
//	@Test(priority = 2)
//	public void Linkedin_Login_Title() {
//
//		try {
//
//			Thread.sleep(2000);
//
//			driver.findElement(By.xpath("//a[contains(@class,'signin')]")).click();
//			String LoginTitle = driver.getTitle();
//			softAssert.assertEquals(LoginTitle, "LinkedIn Login, Sign in | LinkedIn",
//					"LinkedIn Login Title validation failed : ");
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		softAssert.assertAll();
//
//	}
//
//	@Test(priority = 3)
//	public void Linkedin_Login() {
//
//		try {
//
//			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("G.jashwanth@gmail.com");
//			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Vasudha@123");
//			driver.findElement(By.xpath("//button[@type='submit']")).click();
//			Thread.sleep(1000);
//
//			String Title = driver.getTitle();
//			softAssert.assertEquals(Title, "(99+) Feed | LinkedIn", "Failed to login to LinkedIn : ");
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		softAssert.assertAll();
//
//	}
}
