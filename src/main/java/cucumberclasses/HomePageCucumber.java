package cucumberclasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.MainPage;


public class HomePageCucumber {
	
	public static HomePage home;
	public static MainPage login;
	public static WebDriver driver;
	
	@Given("^I am logged on to the Home Page$")
	public void i_am_logged_on_to_the_Home_Page() throws Throwable {
	    
//		File file = new File("src/main/resources/chromedriver.exe");
		File file = new File("C:\\Users\\Administrator\\.jenkins\\workspace\\Project 2\\src\\main\\resources\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
		driver = new ChromeDriver();
		login = new MainPage(driver);
		home = new HomePage(driver);
		
		Properties props = new Properties();
//		FileInputStream in = new FileInputStream("src/main/resources/login.properties");
		FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\.jenkins\\workspace\\Project 2\\src\\main\\resources\\login.properties");
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

	@When("^I click on the user guide link$")
	public void i_click_on_the_user_guide_link() throws Throwable {
		home.getUserGuideLink().click();
	    
	}

	@Then("^I will see the User Guide on github$")
	public void i_will_see_the_User_Guide_on_github() throws Throwable {
	    
		Assert.assertEquals(driver.getTitle(), "Home · revaturelabs/caliber Wiki · GitHub");
		
		driver.quit();
	}

}
