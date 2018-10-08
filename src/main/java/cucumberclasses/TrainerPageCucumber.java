package cucumberclasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import cucumber.api.PendingException;
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

	private int tableLengthBefore = 0;
	private int tableLengthAfter = 0;

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

	@When("^I click on the Trainers tab$")
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

	@When("^I click on Create Trainer\\+$")
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

		Assert.assertEquals(trainerMenuOpen, true);

		//For AddTrainerValidInput (Get count of all trainers before adding one)
		List<WebElement> rows = trainer.getAllTrainersTable().findElements(By.tagName("tr"));
		tableLengthBefore = rows.size();
		driver.quit();
	}

	//Presumes logged in
	@Given("^I am on the Trainers Page$")
	public void i_am_on_the_Trainers_Page() throws Throwable {
		i_click_on_Settings();
		i_click_on_the_Trainers_tab();
	}

	//Presumes logged in
	@Given("^I am in the Add Trainer menu$")
	public void i_am_in_the_Add_Trainer_menu() throws Throwable {
		i_click_on_Settings();
		i_click_on_the_Trainers_tab();
		trainer.getCreateTrainerButton().click();
	}

	@When("^I properly fill in Trainer Name, Title, Email, and Tier$")
	public void i_properly_fill_in_Trainer_Name_Title_Email_and_Tier() throws Throwable {
		//Trainer Name
		trainer.getTrainerNameTextBox().sendKeys("Mr. Trainer");

		//Title
		trainer.getTitleTextBox().sendKeys("Big Man");

		//Email
		trainer.getEmailTextBox().sendKeys("bigman57@darntootin.net");

		//Tier
		Select se = new Select(trainer.getTierDropdown());
		se.selectByValue("TRAINER");
	}

	@When("^I click Save$")
	public void i_click_Save() throws Throwable {
		trainer.getAddTrainerSaveButton().click();
	}

	//TODO: test if the new trainer added matches the one I typed
	//TODO: Fix bug why tableLengthAfter returns 0
	//Should not pass. Caliberbot apparently has no authorization to add trainers.
	@Then("^a new Trainer should appear on the list of Trainers with the proper fields I filled in\\.$")
	public void a_new_Trainer_should_appear_on_the_list_of_Trainers_with_the_proper_fields_I_filled_in() throws Throwable {
		//Make sure that a new trainer was added in
		try {
			List<WebElement> rows = trainer.getAllTrainersTable().findElements(By.tagName("tr"));
			tableLengthAfter = rows.size();
		} catch (NoSuchElementException e){
			e.printStackTrace();
		}
		driver.quit();

		Assert.assertEquals(tableLengthBefore, tableLengthAfter-1);
	}

	@Given("^the Trainer name field is filled in.$")
	public void the_trainer_name_field_is_filled_in() {
		trainer.getTrainerNameTextBox().sendKeys("Wibble Wobble McGee");
	}
	
	@When("^the Email field has a valid email$")
	public void the_Email_field_has_a_valid_email() throws Throwable {
		trainer.getEmailTextBox().sendKeys("    	brugggenheim@email.com");
		
		/*//Validate
		String email = trainer.getEmailTextBox().getText();
		try {
			boolean emailValid = false;
			String errorStr = "";
			if(email.contains("@")) {
				; //S'all good
			} else {
				errorStr = "ERROR: Email does not contain '@'.";
			}
			
			if (!emailValid) {
				throw new IllegalArgumentException(errorStr);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}*/
	}

	@When("^the email has whitespace to the left or right of it$")
	public void the_email_has_whitespace_to_the_left_or_right_of_it() throws Throwable {
		String email = trainer.getEmailTextBox().getText();
		trainer.getEmailTextBox().sendKeys(" 	  ");
		;
		
		//Check for trailing or leading whitespace (only checks first and last characters)
		try {
			if (Character.isWhitespace(email.charAt(0)) && Character.isWhitespace(email.charAt(email.length() - 1))){
				;
			} else {
				throw new IllegalArgumentException("ERROR: ");
			}
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Then("^an error message should NOT appear below the ?Email? textbox saying ?Please enter an email address\\.?$")
	public void an_error_message_should_NOT_appear_below_the_Email_textbox_saying_Please_enter_an_email_address() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


	//driver.quit(); //Move to last sentence listed per feature (@Then, @And, or @But)
}
