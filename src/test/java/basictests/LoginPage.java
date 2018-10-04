// Dont use this a test
// This is more of a playground

package basictests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.HomePage;
import pages.MainPage;


public class LoginPage {
	public static MainPage login;
	public static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		login = new MainPage(driver);
		HomePage home = new HomePage(driver);
		
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
		login.getHomeLink().click();
//		home.getWeeklyAuditStateDropdown().click();
		List<WebElement> states = home.getWeeklyAuditStates();
		states.get(0).click();
		List<WebElement> cities = home.getWeeklyAuditCities();
		cities.get(0).click();
		
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

		Thread.sleep(1500); // dont use this in actualy tests...
		driver.quit();
	}
}
