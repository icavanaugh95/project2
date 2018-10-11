package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.MainPage;
import pages.TrainerPage;

public class EditTrainerTests {

	public static MainPage login;
	public static WebDriver driver;
	public static TrainerPage trainer;
	
	@BeforeSuite
	private void loginAndOpenTrainersPage() throws IOException {
		File file = new File("src/main/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		login = new MainPage(driver);
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
		login.getSettingsDropdown().click();
		login.getTrainersLinkFromSettings().click();
	}
	
/*	Given I am logged in to the Caliber website
	When I click on Settings
		And I click on the Trainers tab
		And I click on any pencil icon
	Then the Edit Trainer menu should appear
		And the proper Trainer Name, Email, Title, and Tier should appear in their respective fields.*/
	@Test
	//This test assume Ravi Singh is the topmost trainer in the table
	public void EditTrainerMenuOpen() {
		
		trainer.getFirstPencilIcon().click();
		
		String 	name = trainer.getTrainerNameTextBox().getText(),
				email = trainer.getEmailTextBox().getText(),
				title = trainer.getTitleTextBox().getText(),
				tier = trainer.getTierDropdown().getText();
		
		System.out.println(name);
		System.out.println(email);
		System.out.println(title);
		System.out.println(tier);
		
		Assert.assertTrue(trainer.getEditTrainerMenu().isDisplayed());
		Assert.assertEquals(name, "Ravi Singh");
		Assert.assertEquals(email, "ravi.singh@revature.comTEST");
		Assert.assertEquals(title, "Chief Technology Officer");
		Assert.assertEquals(tier, "VP");
	}
	
}
