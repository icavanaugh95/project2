package cucumberclasses;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AssessBatchPage;
import pages.HomePage;
import pages.MainPage;
import pages.TrainerPage;


public class NavBarCucumber {

	public static HomePage home;
	public static MainPage login;
	public static AssessBatchPage assess;
	public static WebDriver driver;




	@Given("^I am logged in to the Caliber website nav$")
	public void i_am_logged_in_to_the_Caliber_website_nav() throws Throwable {

		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
		driver = new ChromeDriver();
		login = new MainPage(driver);
		home = new HomePage(driver);

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

	@When("^I click on the Home button$")
	public void i_click_on_the_Home_button() throws Throwable {
		login.getHomeLink().click();
	}

	@Then("^I should see the Home page information$")
	public void i_should_see_the_Home_page_information() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "https://dev-caliber.revature.tech/caliber/#/vp/home");
		
		driver.quit();
	}

	@When("^I click on the Manage Batch$")
	public void i_click_on_the_Manage_Batch() throws Throwable {
		login.getManageBatchLink().click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://dev-caliber.revature.tech/caliber/#/vp/home")));
	}

	@Then("^I should see the Manage Batch page$")
	public void i_should_see_the_Manage_Batch_page() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "https://dev-caliber.revature.tech/caliber/#/vp/manage");
		
		driver.quit();
	}

	@When("^I click on the Assess Batch$")
	public void i_click_on_the_Assess_Batch() throws Throwable {
		login.getAccessBatchLink().click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://dev-caliber.revature.tech/caliber/#/vp/home")));
	}

	@Then("^I should be in the Assess Batch page$")
	public void i_should_be_in_the_Assess_Batch_page() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "https://dev-caliber.revature.tech/caliber/#/vp/assess");
		
		driver.quit();
	}

	@When("^I click on the Quality Audit$")
	public void i_click_on_the_Quality_Audit() throws Throwable {
		login.getQualityAuditLink().click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://dev-caliber.revature.tech/caliber/#/vp/home")));
	}

	@Then("^i should be in the Quality Audit page$")
	public void i_should_be_in_the_Quality_Audit_page() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "https://dev-caliber.revature.tech/caliber/#/vp/audit");
		
		driver.quit();
	}

	@When("^I click on the Panel$")
	public void i_click_on_the_Panel() throws Throwable {
		login.getPanelLink().click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://dev-caliber.revature.tech/caliber/#/vp/home")));
	}

	@Then("^I should be in the Panel page$")
	public void i_should_be_in_the_Panel_page() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "https://dev-caliber.revature.tech/caliber/#/vp/panels");
		
		driver.quit();
	}

	@When("^I click on the Reports$")
	public void i_click_on_the_Reports() throws Throwable {
		login.getReportsLink().click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://dev-caliber.revature.tech/caliber/#/vp/home")));
	}

	@Then("^i should be in the Reports page$")
	public void i_should_be_in_the_Reports_page() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "https://dev-caliber.revature.tech/caliber/#/vp/reports");

		driver.quit();
	}

	@When("^I click on the Setting$")
	public void i_click_on_the_Setting() throws Throwable {
		login.getSettingsDropdown().click();

	}

	@Then("^I should see Trainers Locations and Category Options$")
	public void i_should_see_Trainers_Locations_and_Category_Options() throws Throwable {
		try {
			login.getCategoryLinkFromSettings();
			login.getLocationsLinkFromSettings();
			login.getTrainersLinkFromSettings();
			Assert.assertTrue(true);

		}catch(Exception e) {
			Assert.fail();
		}finally {
			driver.quit();
		}

	}

	@Given("^Settings have been revealed$")
	public void settings_have_been_revealed() throws Throwable {
		login.getSettingsDropdown().click();


	}

	@When("^I click on Trainers$")
	public void i_click_on_Trainers() throws Throwable {
		login.getTrainersLinkFromSettings().click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://dev-caliber.revature.tech/caliber/#/vp/home")));
	}

	@Then("^I should see the Trainers page$")
	public void i_should_see_the_Trainers_page() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "https://dev-caliber.revature.tech/caliber/#/vp/trainers");
		
		driver.quit();
	}

	@When("^I click on Location$")
	public void i_click_on_Location() throws Throwable {
		login.getLocationsLinkFromSettings().click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://dev-caliber.revature.tech/caliber/#/vp/home")));
	}

	@Then("^I should see the Location page$")
	public void i_should_see_the_Location_page() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "https://dev-caliber.revature.tech/caliber/#/vp/locations");
		
		driver.quit();
	}

	@When("^I click on the Category$")
	public void i_click_on_the_Category() throws Throwable {
		login.getCategoryLinkFromSettings().click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://dev-caliber.revature.tech/caliber/#/vp/home")));
	}

	@Then("^I should see the Category page$")
	public void i_should_see_the_Category_page() throws Throwable {
		Assert.assertEquals(driver.getCurrentUrl(), "https://dev-caliber.revature.tech/caliber/#/vp/category");
		
		driver.quit();
	}



}
