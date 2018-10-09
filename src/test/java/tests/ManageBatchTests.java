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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ManageBatchPage;



public class ManageBatchTests {
	public static MainPage login;
	public static WebDriver driver;
	public static ManageBatchPage manage;
	
	@BeforeSuite void TestingCreateBatch() throws IOException {
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
	
	
	
	
	
	
	
   	@Test(dataProvider = "Newest Batch")
	public void nextBatch(String item) {

   		WebDriverWait wait = new WebDriverWait(driver, 20);
   	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='manage']")));
   		System.out.println(item);
        manage.getCreateBatch().click();
        WebDriverWait wait1 = new WebDriverWait(driver, 20);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
        manage.getTrainingName().click();
   		manage.getTrainingName().sendKeys(item);
   		String text = manage.getTrainingName().getAttribute("value");
   	    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='createBatchModal']")));
		Assert.assertTrue(text.equals(item));
	}
    
    @DataProvider(name = "Newest Batch")
    public static Object[][] nextBatchItems(){
    	
    	return new Object[][] {{"Dave Barnes"}};
    }


}//end of ManageBatchTests

