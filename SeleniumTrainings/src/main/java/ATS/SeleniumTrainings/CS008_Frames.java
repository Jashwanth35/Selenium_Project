package ATS.SeleniumTrainings;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CS008_Frames {
	public static WebDriver driver = null;
	static SoftAssert softAssert = new SoftAssert();
	public static Logger log = Logger.getLogger(CS008_Frames.class.getName());

	@Test
	public void DragAndDrop() throws IOException {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--silent");
			chromeOptions.addArguments("--disable-notifications");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();
			
			driver.get("https://jqueryui.com/droppable/");
			WebElement frame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));

			driver.switchTo().frame(frame);
			log.info("Droppable");
			WebElement dragabble = driver.findElement(By.xpath("//div[contains(@class,'ui-widget-content')]"));
			WebElement dragTo = driver.findElement(By.xpath("//div[contains(@class,'ui-widget-header')]"));
			Thread.sleep(1000);
			Actions actions = new Actions(driver);
			actions.dragAndDrop(dragabble, dragTo).build().perform();
			log.info("Droppable");
			
			Thread.sleep(1000);
			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}