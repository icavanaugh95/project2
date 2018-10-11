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

public class ManageBatchTests {
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
//	login.getHomeLink().click();
		login.getManageBatchLink().click();
		
		

	}
	
	@BeforeMethod
	public void goToManageBatch() {

		login.waitForNavBar();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id='manage']")));
		login.getManageBatchLink().click();
		

	}

	@Test(dataProvider = "Newest Batch", priority=1)
	public void nextBatch1(String item) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='manage']")));		//the div that holds the tabs 2018, CreateBatch, ImportBatch
		manage.getCreateBatch().click();
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));	
		manage.getTrainingName().click();
		manage.getTrainingName().sendKeys(item);
		String text = manage.getTrainingName().getAttribute("value");
		manage.getCloseCreateBatch().click();
		
		wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));

		Assert.assertTrue(text.equals(item));
	}

	@DataProvider(name = "Newest Batch")
	public static Object[][] nextBatchItems() {

		return new Object[][] { { "Dave Barnes" } };
	}



	@Test(dataProvider = "NewestBatch5", priority=2)
	public void nextBatch5(String item) {


		WebDriverWait wait = new WebDriverWait(driver, 20);
		manage.getCreateBatch().click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
		manage.getGoodGrade().click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#goodGrade")));
		manage.getGoodGrade().sendKeys(item);
		String text = manage.getGoodGrade().getAttribute("value");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id='createBatchModal']")));
		manage.getCloseCreateBatch().click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
		Assert.assertTrue(text.equals(item));

		

	}

	@DataProvider(name = "NewestBatch5")
	public static Object[][] nextBatchItems5() {

		return new Object[][] { { "60" } };
	}
       
    

	@Test(priority=3)
	public void nextBatch3() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
		manage.getCreateBatch().click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
	    manage.getTrainer().click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#trainer")));
		manage.getCloseCreateBatch().click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
	    
	}

	@Test(priority=4)
	public void nextBatch2() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		//driver.get("https://dev-caliber.revature.tech/caliber/#/vp/manage");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
		manage.getCreateBatch().click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
	    manage.getSkillType().click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#skillType")));
		manage.getCloseCreateBatch().click();
	    
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
	}
	
	
		
	@Test(dataProvider = "NewestBatch4",priority=5)
	public void nextBatch4(String item) {
	

			WebDriverWait wait = new WebDriverWait(driver, 20);
			manage.getCreateBatch().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
			manage.getStartDate().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#start-date")));
			manage.getStartDate().sendKeys(item);
			String text = manage.getStartDate().getAttribute("value");
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[id='createBatchModal']")));
			manage.getCloseCreateBatch().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
			Assert.assertFalse(text.toString().equals(item.toString()));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
		}

		@DataProvider(name = "NewestBatch4")
		public static Object[][] nextBatchItems4() {

			return new Object[][] { { "11-22-2018" } };
		}
		
		@Test(priority=6)
		public void nextBatch6() {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			//driver.get("https://dev-caliber.revature.tech/caliber/#/vp/manage");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
			manage.getCreateBatch().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
		    manage.getTrainingType().click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#trainingType")));
			manage.getCloseCreateBatch().click();
		    
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
		}
		
		@Test(priority=7)
		public void nextBatch7() {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			//driver.get("https://dev-caliber.revature.tech/caliber/#/vp/manage");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
			manage.getCreateBatch().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
		    manage.getLocation().click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#location")));
			manage.getCloseCreateBatch().click();
		    
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
		}
		
		@Test(priority=8)
		public void nextBatch8() {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			//driver.get("https://dev-caliber.revature.tech/caliber/#/vp/manage");
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
			manage.getCreateBatch().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
		    manage.getCoTrainer().click();
		    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#co-trainer")));
			manage.getCloseCreateBatch().click();
		    
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
		}
		
		
	@Test(dataProvider = "NewestBatch9",priority=9)
	public void nextBatch9(String item) {
	

			WebDriverWait wait = new WebDriverWait(driver, 20);
			manage.getCreateBatch().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
			manage.getEndDate().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#end-date")));
			manage.getEndDate().sendKeys(item);
			String text = manage.getEndDate().getAttribute("value");
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[id='createBatchModal']")));
			manage.getCloseCreateBatch().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
			Assert.assertFalse(text.toString().equals(item.toString()));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
		}

		@DataProvider(name = "NewestBatch9")
		public static Object[][] nextBatchItems9() {

			return new Object[][] { { "11-23-2018" } };
		}
		

		@Test(dataProvider = "NewestBatch10", priority=10)
		public void nextBatch10(String item) {


			WebDriverWait wait = new WebDriverWait(driver, 20);
			manage.getCreateBatch().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#manage > div:nth-child(1) > div > div > ul > li:nth-child(3) > a ")));
			manage.getPassingGrade().click();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#borderlineGrade")));
			manage.getPassingGrade().sendKeys(item);
//			wait.until(ExpectedConditions.textToBePresentInElement(By.id("#createBatchModal > div > div > div.modal-body > div:nth-child(5) > div:nth-child(2)"), "55"));
			String text = manage.getPassingGrade().getAttribute("value");
			System.out.println(text);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[id='createBatchModal']")));
			manage.getCloseCreateBatch().click();
			
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
			Assert.assertTrue(text.equals(item));

		}
		
		@DataProvider(name = "NewestBatch10")
		public static Object[][] nextBatchItems10() {

			return new Object[][] { { "0" } };
		}
		
		

}//end of ManageBatchTests

