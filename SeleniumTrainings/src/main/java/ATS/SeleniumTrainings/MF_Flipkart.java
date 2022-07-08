package ATS.SeleniumTrainings;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MF_Flipkart {

	public static WebDriver myD;
	public static String vSearch;
	public static String vURL;
	public static String vTitle;
	public static String ParentWindow, ChildWindow;
	public static String vScreenshot;

	public static void main(String[] args) throws InterruptedException {

		vSearch = "Samsung S22 Ultra";
		vURL = "https://www.flipkart.com/";

		myPrint(TS001OPENBROWSER());
		myPrint(TS002OPENAPP());
		WaitStatement(2000);
		myPrint(TS003CLOSELOGINPOPUP());
		myPrint(TS005SearchItem(vSearch));
		myPrint(TS006ClickSearch());
		myPrint(TS007ClickSearchResultText());
		WindowHandlingFunction();
		myPrint(TS008CaptureandVerifyTitle());
		WaitStatement(1000);
		myPrint(TS004CLOSEAPP());

	}

	public static String TS001OPENBROWSER() throws InterruptedException {

		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		WebDriverManager.chromedriver().setup();
		myD = new ChromeDriver();
		myD.manage().window().maximize();
		Thread.sleep(1000);

		return "TS001 PASS";
	}

	public static String TS002OPENAPP() {
		myD.get(vURL);
		myD.manage().window().maximize();
		myD.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		return "TS002 PASS";
	}

	public static String TS003CLOSELOGINPOPUP() {
		myD.findElement(By.xpath("//*[@class='_2KpZ6l _2doB4z']")).click();
		return "TS003 PASS";
	}

	public static String TS004CLOSEAPP() {
		myD.quit();
		return "TS008 PASS";
	}

	public static void WaitStatement(int waitTime) throws InterruptedException {
		Thread.sleep(waitTime);

	}

	public static void myPrint(String vPrint) {
		System.out.println(vPrint);
	}

	public static String TS005SearchItem(String vSearch) {
		myD.findElement(By.className("_3704LK")).sendKeys(vSearch);

		return "TS004 PASS";
	}

	public static String TS006ClickSearch() {
		myD.findElement(By.className("L0Z3Pu")).click();
		return "TS006 PASS";
	}

	public static String TS007ClickSearchResultText() {

		myD.findElement(By.xpath("(//div[contains(text(),'SAMSUNG')])[2]")).click();
		return "TS006 PASS";
	}

	public static String TS008CaptureandVerifyTitle() {

		vTitle = myD.getTitle();
		String screenshot = takeScreenshot("vTitle.jpg");
		vScreenshot = System.getProperty("user.dir") + "\\screenshots\\" + screenshot;
		System.out.println("Path : " + vScreenshot);

		if (vTitle.contains(vSearch) || vTitle.contains(
				"SAMSUNG Galaxy S22 ultra 5G ( 256 GB Storage, 12 GB RAM ) Online at Best Price On Flipkart.com")) {
			// System.out.println("Title is "+vTitle);
			return "TS007 PASS TITLE VERIFIED";

		}

		else {
			// System.out.println("Title is "+vTitle);
			return "TS007 FAIL TITLE VERIFIED";
		}

	}

	public static void WindowHandlingFunction() {
		Set<String> ids = myD.getWindowHandles();
		Iterator<String> it = ids.iterator();
		ParentWindow = it.next();
		ChildWindow = it.next();
		myD.switchTo().window(ChildWindow);

	}

	public static String takeScreenshot(String fileName) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String destFile = dateFormat.format(new Date()) + "_" + fileName; 
		File scrFile = ((TakesScreenshot) myD).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "\\screenshots\\" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile;

	}

}
