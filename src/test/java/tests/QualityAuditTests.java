package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.MainPage;
import pages.QualityAuditPage;

public class QualityAuditTests {
	public static MainPage login;
	public static QualityAuditPage qc;
	public static WebDriver driver;
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUpDriverAndPage() throws IOException {
		File file = new File("src/main/resources/chromedriver.exe");
		Properties props = new Properties();
		FileInputStream in = new FileInputStream("src/main/resources/login.properties");
		String url, username, password;

		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		props.load(in);

		driver = new ChromeDriver();
		login = new MainPage(driver);
		qc = new QualityAuditPage(driver);
		url = props.getProperty("url");
		username = props.getProperty("username");
		password = props.getProperty("password");

		driver.get(url);
		login.getUsername().sendKeys(username);
		login.getPassword().sendKeys(password);
		login.getLoginButton().click();
	}

	@BeforeMethod
	public void goToAuditPage() {
		login.getQualityAuditLink().click();
	}

	/*
	 * Checks data for all the years for duplicate data
	 */
	@Test
	public void yearsDropdownData() {
		List<String> data = new ArrayList<String>();
		List<WebElement> list;

		qc.getDropdownYear().click();

		list = qc.getYearsFromDropdown();

		for (WebElement lst : list) {
			data.add(lst.getAttribute("innerHTML"));
		}

		Assert.assertFalse(data.size() > (new HashSet<String>(data)).size());
	} // end yearsDropdownData

	/*
	 * Flaky test :(
	 * Adds a note to every associates in the latest week of a batch
	 */
	@Test
	public void associateNote() {
		String item = "I wrote this note";
		wait = new WebDriverWait(driver, 10);
		qc.waitForDiv();
		qc.getDropdownTrainer().click();
		qc.getTrainersFromDropdown().get(1).click();
		List<WebElement> list = qc.getTextBoxes();
		qc.waitForDiv();

		// skip last 1
		// its the overall note for the batch
		for (int i = 0; i < list.size() - 1; i++) {
			qc.waitForDiv();
			wait.until(ExpectedConditions.elementToBeClickable(list.get(i)));
			list.get(i).clear();
			wait.until(ExpectedConditions.elementToBeClickable(list.get(i)));
			list.get(i).sendKeys(item);
			wait.until(ExpectedConditions.elementToBeClickable(list.get(i)));
		}

		// skip last 1
		// its the overall note for the batch
		for (int i = 0; i < list.size() - 1; i++) {
			if (!list.get(i).getAttribute("value").equals(item))
				Assert.fail(list.get(i).getAttribute("value") + " doesnt equal " + item);
		}

		Assert.assertTrue(true);
	}

	/*
	 * Add overall batch note
	 */
	@Test
	public void testBatchNote() {
		String item = "Batch Note";

		List<WebElement> list = qc.getTextBoxes();
		qc.waitForDiv();
		qc.getDropdownTrainer().click();
		qc.getTrainersFromDropdown().get(1).click();

		list.get(list.size() - 1).clear();
		list.get(list.size() - 1).sendKeys(item);

		Assert.assertTrue(item.equals(list.get(list.size() - 1)
				.getAttribute("value")));
	}

	@Test
	public void addWeek() {
		
	}
	
	@AfterSuite
	public void cleanup() {
		driver.quit();
	}
}
