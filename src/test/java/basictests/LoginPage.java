// Dont use this a test
// This is more of a playground

package basictests;

import java.io.File;
import java.io.FileInputStream;
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


public class LoginPage {
	public static MainPage login;
	public static WebDriver driver;				//static variables are blue in STS	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		login = new MainPage(driver);
		HomePage home = new HomePage(driver);
		ManageBatchPage manage = new ManageBatchPage(driver);
		
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
//		login.getHomeLink().click();
		login.getManageBatchLink().click();
		
//		ManageBatchPage tester = new ManageBatchPage(driver);
//		tester.getCreateBatch().click();
		
//		HomePage tester2 = new HomePage(driver);
//		tester2.getLastAuditStateDropdown().click();
//		tester2.getLastAuditStates().get(0).click();
//		tester2.getLastAuditCityDropdown().click();
//		tester2.getLastAuditCities().get(0).click();
		
		
		ManageBatchPage tester = new ManageBatchPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='manage']")));
        tester.getCreateBatch().click();
        WebDriverWait wait1 = new WebDriverWait(driver, 20);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getTrainingName().click();
        tester.getTrainingName().sendKeys("Dave Barnes");
        WebDriverWait wait2 = new WebDriverWait(driver, 20);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getSkillType().click();
        tester.getSkillType().sendKeys(Keys.ESCAPE);
        WebDriverWait wait3 = new WebDriverWait(driver, 20);
        wait3.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getTrainer().click();
        tester.getTrainer().sendKeys(Keys.ESCAPE);
        WebDriverWait wait4 = new WebDriverWait(driver, 20);
        wait4.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getStartDate().click();
        WebDriverWait wait5 = new WebDriverWait(driver, 20);
        wait5.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getGoodGrade().click();
        WebDriverWait wait6 = new WebDriverWait(driver, 20);
        wait6.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getTrainingType().click();
        tester.getTrainingType().sendKeys(Keys.ESCAPE);
        WebDriverWait wait7 = new WebDriverWait(driver, 20);
        wait7.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getLocation().click();
        tester.getLocation().sendKeys(Keys.ESCAPE);
        WebDriverWait wait8 = new WebDriverWait(driver, 20);
        wait8.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getCoTrainer().click();
        tester.getCoTrainer().sendKeys(Keys.ESCAPE);
        WebDriverWait wait9 = new WebDriverWait(driver, 20);
        wait9.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getEndDate().click();
        WebDriverWait wait10 = new WebDriverWait(driver, 20);
        wait10.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        tester.getPassingGrade().click();
        
 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       // tester.getCloseCreateBatch().click();
        
        
//       ManageBatchPage tester1 = new ManageBatchPage(driver);
//       WebDriverWait wait1 = new WebDriverWait(driver, 20);
//       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
//       tester1.getImportBatch().click();
//		tester.getYearDropdown().click();
//		tester.getYearSelector().click();
//		tester.getYears();
//		

		
		
//		cities.get(0).click();
//		login.getManageBatchLink().click();
//		login.getLogoLink().click();
//		login.getHomeLink().click();
//		login.getAccessBatchLink().click();
//		login.getQualityAuditLink().click();
//		login.getPanelLink().click();
//		login.getReportsLink().click();
//		login.getSettingsDropdown().click();
//		login.getTrainersLinkFromSettings().click();
//		login.getSettingsDropdown().click();
//		login.getLocationsLinkFromSettings().click();
//		login.getSettingsDropdown().click();
//		login.getCategoryLinkFromSettings().click();

		Thread.sleep(2500); // dont use this in actualy tests...
		driver.quit();
	}
}
