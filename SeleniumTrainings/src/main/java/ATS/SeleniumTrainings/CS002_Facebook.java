package ATS.SeleniumTrainings;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class CS002_Facebook {
	public static class FacebookLogin {
		public static WebDriver driver = null;

		public static void main(String[] args) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Browser: ");
			String browser = scanner.nextLine();
			System.out.println("Given browser : " + browser);
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
				WebDriverManager.chromedriver().setup();

//				CASE STUDY 2:
//					Do not print any driver informations in the log.
				 Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
			        System.setProperty("webdriver.chrome.silentLogging", "true");
			        System.setProperty("webdriver.chrome.verboseLogging", "false");
			        System.setProperty("webdriver.chrome.silentOutput", "true");


				driver = new ChromeDriver();
			}
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
	

			driver.manage().window().maximize();
			driver.get("https://www.facebook.com");
			String Title = driver.getTitle();
			System.out.println("Actual Title " + Title);
			if (Title.contentEquals("Facebook – log in or sign up")) {
				System.out.println("Title validation - Passed");
			} else {
				System.out.println("Title validation - Failed");
			}
		}
		
	}
	
}
