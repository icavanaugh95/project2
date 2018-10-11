package tests;

import java.io.File;
import java.io.FileInputStream;
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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.AssessBatchPage;
import pages.MainPage;
import pages.QualityAuditPage;


public class AssessBatchTests {

	public static MainPage login;
	public static WebDriver driver;
	public static AssessBatchPage assess;

	@BeforeSuite
	public void setUpDriverAndPage() throws IOException {
		//File file = new File("C:\\Users\\Administrator\\.jenkins\\workspace\\Project 2\\src\\main\\resources\\chromedriver.exe");
		File file = new File("src/main/resources/chromedriver.exe");
		Properties props = new Properties();
		//FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\.jenkins\\workspace\\Project 2\\src\\main\\resources\\login.properties");
		FileInputStream in = new FileInputStream("src/main/resources/login.properties");
		String url, username, password;

		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		props.load(in);

		driver = new ChromeDriver();
		login = new MainPage(driver);
		assess = new AssessBatchPage(driver);
		url = props.getProperty("url");
		username = props.getProperty("username");
		password = props.getProperty("password");

		driver.get(url);
		login.getUsername().sendKeys(username);
		login.getPassword().sendKeys(password);
		login.getLoginButton().click();
		login.waitForNavBar();
		login.getAccessBatchLink().click();
	}

	// Can't have multiple of one year
	@Test
	public void ValidYears() {
		
		List<String> data = new ArrayList<String>();

		List<WebElement>list = assess.getYears();

		for (WebElement lst : list) {
			data.add(lst.getAttribute("innerHTML"));
		}

		Assert.assertFalse(data.size() > (new HashSet<String>(data)).size());


	}
	
	//tests being able to create an assessment for a batch
	@Test
	public void createAssessmentTest() throws InterruptedException {
		assess.getYearSelector().click();
		assess.getYears().get(1).click();
		
		
		assess.createAssesmentButton().click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.elementToBeClickable(assess.getCreateAssessmentSave()));
		assess.getCreateAssessmentCategories().get(8).click();
		assess.getAssessmentTypeCategories().get(3).click();
		assess.maxPoints().sendKeys("50");
		
		int before = assess.getTableHeaders().size();
		assess.getCreateAssessmentSave().click();
		wait.until(ExpectedConditions.invisibilityOf(assess.getCreateAssessmentSave()));
		
		Assert.assertTrue(assess.getTableHeaders().size() == (before + 1));
		
		
	}
	
	@AfterSuite
	public void cleanUpDriver() {
		driver.quit();
	}


}
