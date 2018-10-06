package cucumberclasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
		
		
		
	}
	
	@When("^I click on Settings$")
	public void i_click_on_Settings() throws Throwable{
		WebElement settings = login.getSettingsDropdown();
		settings.click();
		
		
	}
	
	@And("^I click on the Trainers tab$")
	public void i_click_on_the_Trainers_tab() throws Throwable {
		WebElement trainers = login.getTrainersLinkFromSettings();
		trainers.click();
	}
	
	//Is there a Create Trainer+ button? If not, this test does not pass. 
	@Then("^the Trainers page should appear on the browser.$")
	public void the_Trainers_page_should_appear_on_the_browser() throws Throwable {
		boolean trainerPageOpen = true;
		try {
			trainer.getCreateTrainerButton();
		} catch (NoSuchElementException e){
			Assert.fail("The trainers page is not open on the browser.");
			trainerPageOpen = false;
		}
		
		driver.quit();
		
		Assert.assertEquals(trainerPageOpen, true);
	}
	
	@When("^I click on ?Create Trainer\\+?$")
	public void i_click_on_Create_Trainer() throws Throwable {
		WebElement createTrainer = trainer.getCreateTrainerButton();
		createTrainer.click();	
	}

	@Then("^the Add Trainer menu should appear.$")
	public void the_Add_Trainer_menu_should_appear() throws Throwable {
		boolean trainerMenuOpen = true;
		try {
			trainer.getCreateTrainerButton();
		} catch (NoSuchElementException e){
			Assert.fail("The trainers page is not open on the browser.");
			trainerMenuOpen = false;
		}
		
		driver.quit();
		
		Assert.assertEquals(trainerMenuOpen, true);
	}
	
	//Presumes logged in
	@Given("^I am on the Trainers Page$")
	public void i_am_on_the_Trainers_Page() throws Throwable {
		i_click_on_Settings();
		i_click_on_the_Trainers_tab();
	}
	
	
	//driver.quit(); //Move to last sentence listed per feature (@Then, @And, or @But)
}
