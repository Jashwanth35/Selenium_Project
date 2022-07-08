package ATS.SeleniumTrainings;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CS005_BrokenLinks {
	public static WebDriver driver = null;
	static SoftAssert softAssert = new SoftAssert();

	@Test
	public void BrokenLinksTest() throws IOException, InterruptedException {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--silent");
			driver = new ChromeDriver(chromeOptions);

			driver.manage().window().maximize();
			driver.get("https://www.google.com");
			String url = "";
			String linktext = "";

			HttpURLConnection huc = null;
			int respCode = 200;
			List<WebElement> links = driver.findElements(By.tagName("a"));
			System.out.println("Total Number of links : " + links.size());

			for (WebElement link : links) {
				url = link.getAttribute("href");
				linktext = link.getText().toString().trim();

				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				respCode = huc.getResponseCode();

				if (respCode >= 400 || linktext.contains("हिन्दी") || linktext.contains("বাংলা")
						|| linktext.contains("తెలుగు") || linktext.contains("मराठी") || linktext.contains("हिन्दी")
						|| linktext.contains("தமிழ்") || linktext.contains("ગુજરાતી") || linktext.contains("हिन्दी")
						|| linktext.contains("ಕನ್ನಡ") || linktext.contains("മലയാളം") || linktext.contains("हिन्दी")
						|| linktext.contains("ਪੰਜਾਬੀ")) {
					//System.out.println("Skiped URL : " + linktext + " : " + url);
				} else {

					System.out.println(linktext + " : " + url);

				}

			}
			
			Thread.sleep(1000);
			driver.close();


		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
}