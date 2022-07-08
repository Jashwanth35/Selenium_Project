package ATS.SeleniumTrainings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DDF_Google {

	public static int xlRows, xlCols;
	public static String xData[][];
	public static WebDriver myD;
	public static String vURL;
	public static String pTitle;
	public static String vSearch;
	public static String vFlag;
	public static String vExpTitle;
	public static String vScreenshot;

	public static void main(String[] args) throws Exception {
		xlRead(System.getProperty("user.dir") + "\\DataRead\\" + "GoogleDataFrameworks.xls");

		vURL = "https://www.google.com/";

		for (int i = 1; i < xlRows; i++) {

			if (xData[i][1].contains("Y") || xData[i][1].contains("y")) {
				vSearch = xData[i][2];
				vExpTitle = xData[i][3];

				OPENAPP();
				SearchProduct();
				CaptureVerifyTitle();
				xData[i][4] = pTitle;

				xData[i][5] = vFlag;
				xData[i][6] = vScreenshot;
				CLOSEAPP();
			}
		}

		xlwrite(System.getProperty("user.dir") + "\\DataRead\\" + "GoogleResult.xls", xData);

	}

	public static void OPENAPP() throws InterruptedException {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		WebDriverManager.chromedriver().setup();
		myD = new ChromeDriver();
		myD.manage().window().maximize();
		myD.get(vURL);
		Thread.sleep(1000);

	}

	public static void SearchProduct() throws InterruptedException {

		myD.findElement(By.xpath("//input[@title='Search']")).sendKeys(vSearch);
		Thread.sleep(2000);
		myD.findElement(By.xpath("(//span[normalize-space(text())=" + "'" + vSearch.toLowerCase() + "'" + "])[1]"))
				.click();

	}

	public static void CaptureVerifyTitle() {
		pTitle = myD.getTitle();
		System.out.println(pTitle);

		if (pTitle.contains(vExpTitle)) {
			vFlag = "PASS";
			System.out.println("PASS");
			String screenshot = takeScreenshot("vExpTitlePASS.jpg");
			vScreenshot = "Path : " + System.getProperty("user.dir") + "\\screenshots " + " & File Name : "
					+ screenshot;
			System.out.println(vScreenshot);
		}

		else {
			vFlag = "FAIL";
			System.out.println("FAIL");
			String screenshot = takeScreenshot("vExpTitleFAIL.jpg");
			vScreenshot = "Path : " + System.getProperty("user.dir") + "\\screenshots " + " & File Name : "
					+ screenshot;
			System.out.println(vScreenshot);

		}

	}

	public static void CLOSEAPP() {
		myD.close();
	}

	public static void xlRead(String sPath) throws Exception {
		File myFile = new File(sPath);
		FileInputStream myStream = new FileInputStream(myFile);

		HSSFWorkbook myWorkbook = new HSSFWorkbook(myStream);
		HSSFSheet mySheet = myWorkbook.getSheetAt(0);
		xlRows = mySheet.getLastRowNum() + 1;
		xlCols = mySheet.getRow(0).getLastCellNum();
		System.out.println("Row Count is " + xlRows);
		System.out.println("Col Count is " + xlCols);

		xData = new String[xlRows][xlCols];

		for (int i = 0; i < xlRows; i++) {
			HSSFRow row = mySheet.getRow(i);

			for (short j = 0; j < xlCols; j++) {
				HSSFCell cell = row.getCell(j);
				String value = cellToString(cell);
				xData[i][j] = value;
				System.out.print(xData[i][j] + "    ");
			}
			System.out.println();
		}
	}

	public static String cellToString(HSSFCell cell) {
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			result = cell.getNumericCellValue();
			break;
		case HSSFCell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			throw new RuntimeException("We cannot evaluate formula");
		case HSSFCell.CELL_TYPE_BLANK:
			result = "-";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			result = cell.getBooleanCellValue();
		case HSSFCell.CELL_TYPE_ERROR:
			result = "This Cell has some error";
		default:
			throw new RuntimeException("We do not evaluate this data");
		}
		return result.toString();
	}

	public static void xlwrite(String xlpath1, String[][] xData) throws Exception {

		File myFile1 = new File(xlpath1);
		FileOutputStream fout = new FileOutputStream(myFile1);
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet mySheet1 = wb.createSheet("TestResults");
		for (int i = 0; i < xlRows; i++) {
			HSSFRow row1 = mySheet1.createRow(i);
			for (short j = 0; j < xlCols; j++) {
				HSSFCell cell1 = row1.createCell(j);
				cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell1.setCellValue(xData[i][j]);
			}
		}
		wb.write(fout);
		fout.flush();
		fout.close();
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
