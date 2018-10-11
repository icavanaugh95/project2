package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MainPage;
import pages.ManageBatchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ManageBatchPage;

import org.openqa.selenium.WebDriver;

import pages.MainPage;

public class CreateBatchTest {
	public static MainPage login;
	public static WebDriver driver;
	public static ManageBatchPage manage;
	
	@BeforeSuite
	void TestingCreateBatch() throws IOException {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		login = new MainPage(driver);
		manage = new ManageBatchPage(driver);

		Properties props = new Properties();
		FileInputStream in = new FileInputStream("src/main/resources/login.properties");
		props.load(in);

		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");

		driver.get(url);
		login.getUsername().sendKeys(username);
		login.getPassword().sendKeys(password);
		login.getLoginButton().click();
		login.waitForNavBar();
		login.getManageBatchLink().click();
		
//	}end of TestingCreateBatch()
//	
//	@BeforeMethod
//	public void goToManageBatch() {
//
//		login.waitForNavBar();
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id='manage']")));
//		login.getManageBatchLink().click();
//		

//	}end of goToManageBatch()

	//@Test(dataProvider = "Newest Batch", priority=1)
	//public void nextBatch1(String item) {
			String item1 = "Dave Barnes' batch";
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='manage']")));		//the div that holds the tabs 2018, CreateBatch, ImportBatch
			manage.getCreateBatch().click();
			WebDriverWait wait1 = new WebDriverWait(driver, 20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));	
			manage.getTrainingName().click();
			manage.getTrainingName().sendKeys(item1);
			String item2 = "60";
			System.out.println(item2);
			manage.getGoodGrade().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#goodGrade")));
			manage.getGoodGrade().sendKeys(item2);
	    	manage.getTrainer().click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#trainer")));
	    	manage.getSkillType().click();
	    	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#skillType")));
	        String item4 = "11-22-2017";
			manage.getStartDate().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#start-date")));
			manage.getStartDate().sendKeys(item4);
		    manage.getTrainingType().click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#trainingType")));
		    manage.getLocation().click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#location")));
		    manage.getCoTrainer2().click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#co-trainer")));
		    String item9 = "12-23-2018";
			manage.getEndDate().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#end-date")));
			manage.getEndDate().sendKeys(item9);
			String item10 = "0";
			manage.getPassingGrade().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#borderlineGrade")));
			manage.getPassingGrade().sendKeys(item10);
			manage.getSaveBatch().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#createBatchModal > div > div > div.modal-footer > input")));
	
	}
}


