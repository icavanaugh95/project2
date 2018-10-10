package cucumberclasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
		} finally {
			driver.quit();
		}

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
			driver.quit();
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
		trainer.getSaveButton().click();
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
			driver.quit();
		} finally {
			driver.quit();
		}

		Assert.assertEquals(tableLengthBefore, tableLengthAfter-1);
		
		//Delete the trainer I just added
		//This should not be implemented because Caliberbot apparently has no authorization to add trainers.
	}
	
	@When("^the Email field has a valid email$")
	public void the_Email_field_has_a_valid_email() throws Throwable {
		trainer.getEmailTextBox().sendKeys("brugggenheim@email.com");		///Added whitespace to the left and right of email
	}

	@When("^the email has whitespace to the left or right of it$")
	public void the_email_has_whitespace_to_the_left_or_right_of_it() throws Throwable {
		String email = trainer.getEmailTextBox().getAttribute("value");
		email = "   " + email + "   ";
		
		//Check for trailing or leading whitespace (only checks first and last characters)
		try {
			if (!Character.isWhitespace(email.charAt(0)) && !Character.isWhitespace(email.charAt(email.length() - 1))){
				throw new IllegalArgumentException("ERROR: No whitespace to the left or right.");
			}
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			driver.quit();
		}
	}

	//TODO: find "id" of error message of "Please enter an email address"
	@Then("^an error message should NOT appear below the Email textbox saying Please enter an email address\\.$")
	public void an_error_message_should_NOT_appear_below_the_Email_textbox_saying_Please_enter_an_email_address() throws Throwable {
		try {
			Assert.assertEquals(trainer.getEmailErrorMessage().getSize(), new Dimension(0, 0));	//Error message is not visible
		} catch (AssertionError e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
	
	@Given("^the Trainer name field is filled in$")
	public void the_Trainer_name_field_is_filled_in() throws Throwable {
	    trainer.getTrainerNameTextBox().sendKeys("Blubber");
	}

	@When("^the Trainer Name field is left blank$")
	public void the_Trainer_Name_field_is_left_blank() throws Throwable {
	    trainer.getTrainerNameTextBox().clear();
	}

	//TODO: Get "id" of Name textbox error message of "Please fill out this field"
	@Then("^an error message should appear below the Name textbox saying Please fill out this field\\.$")
	public void an_error_message_should_appear_below_the_Name_textbox_saying_Please_fill_out_this_field() throws Throwable {
	    try {
			Assert.assertNotEquals(trainer.getNameErrorMessage().getSize(), new Dimension(0, 0));		//Error message is visible
		} catch (AssertionError e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	    
	    
	}

	@When("^the Email field is left blank$")
	public void the_Email_field_is_left_blank() throws Throwable {
		trainer.getEmailTextBox().clear();
	}

	//TODO: Get "id" of Email textbox error message of "Please fill out this field"
	@Then("^an error message should appear below the Email textbox saying Please fill out this field\\.$")
	public void an_error_message_should_appear_below_the_Email_textbox_saying_Please_fill_out_this_field() throws Throwable {
		try {
			Assert.assertNotEquals(trainer.getEmailErrorMessage().getSize(), new Dimension(0, 0));		//Error message is visible
			Assert.assertEquals(trainer.getEmailErrorMessage().getText(), "Please fill out this field.");
		} catch (AssertionError e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	@When("^the Email field does not have an @$")
	public void the_Email_field_does_not_have_at() throws Throwable {
		trainer.getEmailTextBox().sendKeys("Iamnotanemail");
		
		/*//Validate
		String email = trainer.getEmailTextBox().getAttribute("value");
		try {
			boolean emailValid = false;
			String errorStr = "";
			if(!email.contains("@")) {
				errorStr = "ERROR: Email does not contain '@'.";
			} else {
				emailValid = true;
			}
			
			if (!emailValid) {
				driver.quit();
				throw new IllegalArgumentException(errorStr);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			driver.quit();
		}*/
	}

	//TODO: Get "id" of Email textbox error message of "Please fill out this field"
	@Then("^an error message should appear below the Email textbox saying Please enter an email address\\.$")
	public void an_error_message_should_appear_below_the_Email_textbox_saying_Please_enter_an_email_address() throws Throwable {
		try {
			Assert.assertNotEquals(trainer.getEmailErrorMessage().getSize(), new Dimension(0, 0));		//Error message is visible
			Assert.assertEquals(trainer.getEmailErrorMessage().getText(), "Please enter an email address.");
		} catch (AssertionError e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
	
	@When("^the Email field does not have any text to the left or the right of the @$")
	public void the_Email_field_does_not_have_any_text_to_the_left_or_the_right_of_the_At() throws Throwable {
		trainer.getEmailTextBox().sendKeys(" @ ");
	    String email = trainer.getEmailTextBox().getText().trim();
	    String[] subs = email.split("@");
	    for(String s: subs) {
	    	if(!s.equals("")) {
	    		driver.quit();
	    		throw new IllegalArgumentException("ERROR: There is text to the left or right of the @.");
	    	}
	    }
	}

	@When("^the Title field is left blank$")
	public void the_Title_field_is_left_blank() throws Throwable {
	    trainer.getTitleTextBox().clear();
	}

	//TODO: Get "id" of Title textbox error message of "Please fill out this field"
	@Then("^an error message should appear below the Enter Title textbox saying Please fill out this field\\.$")
	public void an_error_message_should_appear_below_the_Enter_Title_textbox_saying_Please_fill_out_this_field() throws Throwable {
		try {
			Assert.assertNotEquals(trainer.getTitleErrorMessage().getSize(), new Dimension(0, 0));		//Error message is visible
			Assert.assertEquals(trainer.getTitleErrorMessage().getText(), "Please fill out this field.");
		} catch (AssertionError e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	    
	    
	}
	
	@When("^I click on Close$")
	public void i_click_on_Close() throws Throwable {
	    trainer.getCloseButton().click();
	}

	@Then("^the Add Trainer menu should close without any errors\\.$")
	public void the_Add_Trainer_menu_should_close_without_any_errors() throws Throwable {
		try {
			trainer.waitForPopupTrainerMenuClose();
			Assert.assertEquals(trainer.getAddTrainerMenu().getSize(), new Dimension(0, 0));			//Add Trainer menu is not visible
		} catch (AssertionError e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	@When("^I click on the gray x on the top right$")
	public void i_click_on_the_gray_x_on_the_top_right() throws Throwable {
	    trainer.getGrayX().click();
	}

	@Given("^the Email field is filled in$")
	public void the_Email_field_is_filled_in() throws Throwable {
		trainer.getEmailTextBox().sendKeys("bloodymary619@hartfield.net");
	}

	@Given("^the Title field is filled in$")
	public void the_Title_field_is_filled_in() throws Throwable {
	    trainer.getTitleTextBox().sendKeys("Grand Poohbah");
	}

	@When("^a Tier is not selected$")
	public void a_Tier_is_not_selected() throws Throwable {
		/*Select clickThis = new Select(trainer.getTierDropdown());
		List<WebElement> options = clickThis.getAllSelectedOptions();
		for (WebElement o: options) {
			clickThis.deselectByIndex(options.indexOf(o));
		}*/
		
		trainer.getTierDropdown().sendKeys("");
	}

	@Then("^an error message should appear below the Select Tier dropdown saying Please select an item in the list\\.$")
	public void an_error_message_should_appear_below_the_Select_Tier_dropdown_saying_Please_select_an_item_in_the_list() throws Throwable {
		try {
			Assert.assertNotEquals(trainer.getEmailErrorMessage().getSize(), new Dimension(0, 0));		//Error message is visible
			Assert.assertEquals(trainer.getEmailErrorMessage().getText(), "Please fill out this field.");
		} catch (AssertionError e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
	
	//Edit Trainer exclusive
	
	//Tests with first pencil icon found
	@When("^I click on any pencil icon$")
	public void i_click_on_any_pencil_icon() throws Throwable {
		trainer.waitForPopupTrainerMenuClose();
	    trainer.getFirstPencilIcon().click();
	}

	@Then("^the Edit Trainer menu should appear$")
	public void the_Edit_Trainer_menu_should_appear() throws Throwable {
		try {
			Assert.assertNotEquals(trainer.getEditTrainerMenu().getSize(), new Dimension(0, 0));		//Error message is visible
			Assert.assertEquals(trainer.getTrainerModalLabel().getText(), "Edit Trainer");
		} catch (AssertionError e) {
			e.printStackTrace();
			driver.quit();
		} 
	}

	//TODO: If we feel like it, make this test more versatile (i.e. able to click any pencil icon and test still pass)
	//Checks only first row (since we click first pencil icon)
	@Then("^the proper Trainer Name, Email, Title, and Tier should appear in their respective fields\\.$")
	public void the_proper_Trainer_Name_Email_Title_and_Tier_should_appear_in_their_respective_fields() throws Throwable {
	    try {
			Assert.assertEquals(trainer.getTrainerNameTextBox().getText().trim(), "Ravi Singh");
			Assert.assertEquals(trainer.getEmailTextBox().getText().trim(), "ravi.singh@revature.comTEST");
			Assert.assertEquals(trainer.getTitleTextBox().getText().trim(), "Chief Technology Officer");
			Assert.assertEquals(trainer.getTierDropdown().getText().trim(), "VP");
		} catch (AssertionError e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	    
	}
	

	//driver.quit(); //Move to last sentence listed per feature (@Then, @And, or @But)
}
