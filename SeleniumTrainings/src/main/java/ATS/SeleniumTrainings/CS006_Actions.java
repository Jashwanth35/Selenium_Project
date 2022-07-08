package ATS.SeleniumTrainings;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CS006_Actions {
	public static WebDriver driver = null;
	static SoftAssert softAssert = new SoftAssert();

	@Test
	public void ActionTest() throws IOException {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();

			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--silent");
			driver = new ChromeDriver(chromeOptions);

			driver.manage().window().maximize();
			driver.get("http://www.youcandealwithit.com/");

			WebElement browser = driver.findElement(By.linkText("BORROWERS"));
			Actions actions = new Actions(driver);
			actions.moveToElement(browser).build().perform();
			Thread.sleep(1000);
			WebElement calc = driver.findElement(By.linkText("Calculators & Resources"));
			actions.click(calc).build().perform();
			Thread.sleep(1000);

			driver.findElement(By.linkText("Calculators")).click();
			Thread.sleep(1000);

			driver.findElement(By.linkText("Budget Calculator")).click();
			Thread.sleep(1000);

			driver.findElement(By.id("food")).sendKeys("300");
			driver.findElement(By.id("clothing")).sendKeys("100");
			driver.findElement(By.id("shelter")).sendKeys("200");

			driver.findElement(By.id("monthlyPay")).sendKeys("2000");
			driver.findElement(By.id("monthlyOther")).sendKeys("200");

			actions.moveToElement(driver.findElement(By.id("underOverBudget"))).build().perform();
			
			//Monthy Expenses
			String totalMonthlyExpenses = driver.findElement(By.id("totalMonthlyExpenses")).getAttribute("value");
			String[] x = totalMonthlyExpenses.split("[.]", 0);
			int MonthlyExpenses = Integer.parseInt(x[0]);
			System.out.println("totalMonthlyExpenses : " + MonthlyExpenses);
			
			//Monthy Income
			String totalMonthlyIncome = driver.findElement(By.id("totalMonthlyIncome")).getAttribute("value");
			String[] y = totalMonthlyIncome.split("[.]", 0);
			int MonthlyIncome = Integer.parseInt(y[0]);
			System.out.println("totalMonthlyIncome : " + MonthlyIncome);

			//Monthy underOverBudget
			String underOverBudget = driver.findElement(By.id("underOverBudget")).getAttribute("value");
			String[] z = underOverBudget.split("[.]", 0);
			int Budget = Integer.parseInt(z[0]);
			System.out.println("underOverBudget : " + Budget);

			if (MonthlyIncome > MonthlyExpenses) {
				System.out.println("your savings are amazing");
			}

			else if (MonthlyIncome < MonthlyExpenses) {
				System.out.println("You need to improve in savings");

			}

			Thread.sleep(1000);
			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}