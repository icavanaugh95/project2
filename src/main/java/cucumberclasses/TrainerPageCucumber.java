package cucumberclasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import pages.HomePage;
import pages.MainPage;
import pages.TrainerPage;

public class TrainerPageCucumber {

	public static HomePage home;
	public static MainPage login;
	public static TrainerPage trainer;
	public static WebDriver driver;
	
	@Given("^I am logged in to the Caliber website$")
	public void i_am_logged_in_to_the_Caliber_website() throws Throwable {
	    
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
		driver = new ChromeDriver();
		login = new MainPage(driver);
		home = new HomePage(driver);
		trainer = new TrainerPage(driver);
		
		Properties props = new Properties();
		FileInputStream in = new FileInputStream("src/main/resources/connection.properties");
		props.load(in);

		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		
		driver.get(url);
		
		login.getUsername().sendKeys(username);
		login.getPassword().sendKeys(password);
		login.getLoginButton().click();
		login.waitForNavBar();
		
	}
	
	
}
