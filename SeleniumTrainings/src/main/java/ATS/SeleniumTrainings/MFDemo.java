package ATS.SeleniumTrainings;


import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MFDemo 
{
	
	
	public static WebDriver myD;
	public static String vSearch;
	public static String vURL;
	public static String vTitle;
	public static String ParentWindow, ChildWindow;
	
	

	public static void main(String[] args) throws InterruptedException 
	{
	
		vSearch = "Samsung S22 Ultra";
		vURL="https://www.flipkart.com/";
		
		
		
//		1. OPEN CHROME BROWSER
		myPrint(TS001OPENBROWSER());
		
//		2. OPEN https://www.flipkart.com/
		myPrint(TS002OPENAPP());

//		2.1 Wait for 10 Seconds
		WaitStatement(10000);
		
//		3. Close the Login POPUP
		myPrint(TS003CLOSELOGINPOPUP());
		
//		4. Enter Samsung S22 Ultra
		myPrint(TS005SearchItem(vSearch));
		
//		5. Click on Search Button
		myPrint(TS006ClickSearch());
		
		
//		6. Click on the First Element
		myPrint(TS007ClickSearchResultText());
		
		
//		7. Capture Title and Verify
		WindowHandlingFunction();
		myPrint(TS008CaptureandVerifyTitle());
		
		
		
//		7.1 Wait for 5 Seconds
		WaitStatement(5000);
		
//		8. Close Browser
		myPrint(TS004CLOSEAPP());


	}
	
	public static String TS001OPENBROWSER() throws InterruptedException
	{
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\SARAVANAN R\\Desktop\\Selenium\\chromedriver.exe");
//		myD=new ChromeDriver();
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		WebDriverManager.chromedriver().setup();
		myD = new ChromeDriver();
		myD.manage().window().maximize();
		Thread.sleep(1000);
		
		return "TS001 PASS";
	}
	
	public static String TS002OPENAPP()
	{
		myD.get(vURL);
		myD.manage().window().maximize();
		myD.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		return "TS002 PASS";
	}
	
	public static String TS003CLOSELOGINPOPUP()
	{
		myD.findElement(By.xpath("//*[@class='_2KpZ6l _2doB4z']")).click();
		return "TS003 PASS";
	}
	
	public static String TS004CLOSEAPP()
	{
		myD.quit();
		return "TS008 PASS";
	}
	
	public static void WaitStatement(int waitTime) throws InterruptedException
	{
//		System.out.println("Waiting for ....."+ waitTime);
		Thread.sleep(waitTime);
		
	}
	
	
	public static void myPrint(String vPrint)
	{
		System.out.println(vPrint);
	}
	
	
	public static String TS005SearchItem(String vSearch)
	{
		myD.findElement(By.className("_3704LK")).sendKeys(vSearch);
		
		return "TS004 PASS";
	}
	
	public static String TS006ClickSearch()
	{
			myD.findElement(By.className("L0Z3Pu")).click();
			return "TS006 PASS";
	}
	
	public static String TS007ClickSearchResultText()
	{
		
		myD.findElement(By.xpath("(//div[contains(text(),'SAMSUNG')])[2]")).click();
		return "TS006 PASS";
	}
	
	public static String TS008CaptureandVerifyTitle()
	{
		
		vTitle=myD.getTitle();
		
		if (vTitle.contains(vSearch) || vTitle.contains("ARUNA"))
		{
			//System.out.println("Title is "+vTitle);
			return "TS007 PASS TITLE VERIFIED";
			
		}
		
		else
		{
			//System.out.println("Title is "+vTitle);
			return "TS007 FAIL TITLE VERIFIED";	
		}
	}
	
	public static void WindowHandlingFunction()
	{
		Set<String> ids=myD.getWindowHandles();
		Iterator<String> it=ids.iterator();
		ParentWindow=it.next();
		ChildWindow=it.next();
		myD.switchTo().window(ChildWindow);
				
	}
	
	

}
